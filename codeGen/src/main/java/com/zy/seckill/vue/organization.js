import request from '@/utils/request'

//**组织机构**

//添加
export function add(data) {
  return request({
    url: '/organization/addOrganization',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/organization/deleteOrganizationById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/organization/updateOrganization',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/organization/getOrganizationById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/organization/getOrganizationListPageVo',
    method: 'post',
    data
  })
}

