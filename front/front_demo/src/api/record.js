import request from '@/utils/request'

const api_name = '/record'

export default {

  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  getById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
    })
  },


  save(question) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: question
    })
  },

  updateById(role) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: role
    })
  },
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  removeRows(idList) {
    return request({
      url: `${api_name}/batchRemove`,
      method: 'delete',
      data: idList
    })
  },

  exportRecord(){
    return request({
      url: `${api_name}/export`,
      method: 'get',
      responseType: 'blob'
    })
  },
  importTemplate(){
    return request({
      url: `${api_name}/getImportTemplate`,
      method: 'post',
      responseType: 'blob'
    })
  },

}
