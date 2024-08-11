import axios from 'axios'
import ElementUI from 'element-ui'

// create an axios instance
const request = axios.create({
  baseURL: process.env.BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
request.interceptors.request.use(
  config => {
   
    return config
  },
  error => {

    return Promise.reject(error)
  }
)

// response interceptor
request.interceptors.response.use(
 
  response => {
     // 文件下载返回二进制流
     if (response.request.responseType === 'blob') {
      return response
    }

    if(response.data.code === 200 ){
      return response.data
    }else{
      Message({
        message:  response.data.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(response.data.message || 'Error'))
    }
  },
  error => {
        return Promise.reject(error)
    }
)

export default request
