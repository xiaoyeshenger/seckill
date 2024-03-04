import request from '@/utils/request'

//**疫苗发放**

//添加
export function add(data) {
  return request({
    url: '/vaccineRelease/addVaccineRelease',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/vaccineRelease/deleteVaccineReleaseById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/vaccineRelease/updateVaccineRelease',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/vaccineRelease/getVaccineReleaseById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/vaccineRelease/getVaccineReleaseListPageVo',
    method: 'post',
    data
  })
}

