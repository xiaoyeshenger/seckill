import request from '@/utils/request'

//**疫苗发放**

//添加
export function add(data) {
  return request({
    url: '/vaccinationRecord/addVaccinationRecord',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/vaccinationRecord/deleteVaccinationRecordById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/vaccinationRecord/updateVaccinationRecord',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/vaccinationRecord/getVaccinationRecordById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/vaccinationRecord/getVaccinationRecordListPageVo',
    method: 'post',
    data
  })
}

