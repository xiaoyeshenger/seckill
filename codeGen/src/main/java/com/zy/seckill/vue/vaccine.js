import request from '@/utils/request'

//**疫苗**

//添加
export function add(data) {
  return request({
    url: '/vaccine/addVaccine',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/vaccine/deleteVaccineById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/vaccine/updateVaccine',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/vaccine/getVaccineById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/vaccine/getVaccineListPageVo',
    method: 'post',
    data
  })
}

