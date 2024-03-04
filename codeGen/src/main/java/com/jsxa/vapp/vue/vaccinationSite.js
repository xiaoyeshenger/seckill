import request from '@/utils/request'

//**接种点**

//添加
export function add(data) {
  return request({
    url: '/vaccinationSite/addVaccinationSite',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/vaccinationSite/deleteVaccinationSiteById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/vaccinationSite/updateVaccinationSite',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/vaccinationSite/getVaccinationSiteById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/vaccinationSite/getVaccinationSiteListPageVo',
    method: 'post',
    data
  })
}

