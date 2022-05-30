import { apiInstance } from "./index.js";

const api = apiInstance();

function listBookmark(userId, success, fail) {
  api.get(`/bookmark/user/${userId}`).then(success).catch(fail);
}

function getBookmark(aptCode, success, fail) {
  api.get(`/bookmark/${aptCode}`).then(success).catch(fail);
}
function deleteBookmark(userId, aptCode, success, fail) {
  api.delete(`/bookmark/${userId}/${aptCode}`).then(success).catch(fail);
}

function writeBookmark(bookmarkInfoDto, success, fail) {
  api.post(`/bookmark`, JSON.stringify(bookmarkInfoDto)).then(success).catch(fail);
}

function getChartData(aptCode, success, fail) {
  api.get(`/bookmark/chart/${aptCode}`).then(success).catch(fail);
}

export { listBookmark, getBookmark, deleteBookmark, writeBookmark, getChartData };
