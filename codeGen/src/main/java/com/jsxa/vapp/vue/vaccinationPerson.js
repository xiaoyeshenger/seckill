import request from '@/utils/request'

//**接种人员**

//添加
export function add(data) {
  return request({
    url: '/vaccinationPerson/addVaccinationPerson',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/vaccinationPerson/deleteVaccinationPersonById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/vaccinationPerson/updateVaccinationPerson',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/vaccinationPerson/getVaccinationPersonById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/vaccinationPerson/getVaccinationPersonListPageVo',
    method: 'post',
    data
  })
}

