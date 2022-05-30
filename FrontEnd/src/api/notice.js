import { apiInstance } from "./index.js";

const api = apiInstance();

function listNotice(success, fail) {
  api.get(`/notices`).then(success).catch(fail);
}
function listRecentNotice(success, fail) {
  api.get(`/notices/recent`).then(success).catch(fail);
}
function getNotice(noticeId, success, fail) {
  api.get(`/notices/${noticeId}`).then(success).catch(fail);
}
function deleteNotice(noticeId, success, fail) {
  api.delete(`/notices/${noticeId}`).then(success).catch(fail);
}

function writeNotice(noticeInfoDto, success, fail) {
  api.post(`/notices`, JSON.stringify(noticeInfoDto)).then(success).catch(fail);
}

function modifyNotice(noticeInfoDto, success, fail) {
  api
    .put(`/notices/${noticeInfoDto.noticeId}`, JSON.stringify(noticeInfoDto))
    .then(success)
    .catch(fail);
}

export { listNotice, listRecentNotice, getNotice, deleteNotice, writeNotice, modifyNotice };
