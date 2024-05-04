import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { loginActions } from '../redux/reducer/pageReducer/loginReducer';


const useAxiosInstance = () => {

    const dispatch=useDispatch()
    const navigate = useNavigate()

    // Redux 스토어에서 액세스 토큰 가져오기
    const accessToken = useSelector((state) => state.login.accessToken);

    // 세션 스토리지에서 리프레시 토큰 가져오기
    const refreshToken = sessionStorage.getItem('refreshToken');

    // Axios 인스턴스 생성
    const instance = axios.create({
        baseURL: 'http://localhost:8080',
        headers: {
            'Authorization': `Bearer ${accessToken}` // 액세스 토큰을 요청 헤더에 포함
        }
    });

    // // 요청 인터셉터 설정
    // instance.interceptors.request.use(
    //     config => {
    //         // 요청 전 추가 작업 수행
    //         return config;
    //     },
    //     error => {
    //         // 요청 에러 발생 시 처리
    //         return Promise.reject(error);
    //     }
    // );

    // 응답 인터셉터 설정
    instance.interceptors.response.use(
        response => {
            // 응답 후 추가 작업 수행
            return response;
        },
        async error => {
            //기존의 요청 저장 
            const originalRequest = error.config;
            originalRequest._retry=false;
            // 응답 에러가 401(인증 실패)이고 요청이 이미 리프레시 토큰을 포함하여 보내진 경우
            // if (error.response.status === 401 && originalRequest._retry===false && refreshToken) {
            if (!accessToken && refreshToken && originalRequest._retry===false) {
                originalRequest._retry = true;
                console.log('시도함')
                try {
                    // 새로운 액세스 토큰 요청
                    const response = await axios.get('/member/reissue-token', 
                    {
                        headers: {
                            Refresh : refreshToken
                        }
                    });
                    const newAccessToken = response.data.accessToken;
                    dispatch(loginActions.getAccessToken(newAccessToken))
                    dispatch(loginActions.getRefreshToken(response.data.refreshToken))
                    
                    //새로운 refreshToken 세션스토리지에 저장
                    window.sessionStorage.setItem('refreshToken',response.data.refreshToken)

                    // 새로 받은 액세스 토큰으로 기존 요청 재시도
                    originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
                    return axios(originalRequest);
                } catch (refreshError) { 
                    // refresh토큰으로 새로운 access토큰 발급후 인증시도를 하고도 에러가 난경우 
                    sessionStorage.removeItem('refreshToken');
                    // 로그인 페이지로 리다이렉트 또는 다른 처리
                    navigate('/login')
                    return Promise.reject(refreshError);
                }
            }

            // 그 외의 에러는 그대로 반환
            return Promise.reject(error);
        }
    );

    return instance;
}

export default useAxiosInstance;