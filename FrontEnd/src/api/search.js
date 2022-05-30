import { apiInstance } from "./index.js";

const api = apiInstance();

function getSidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}
function getGugunList(sidoCode, success, fail) {
  api.get(`/map/gugun`, { params: sidoCode }).then(success).catch(fail);
}
function getDongList(gugunCode, success, fail) {
  api.get(`/map/dong`, { params: gugunCode }).then(success).catch(fail);
}

export { getSidoList, getGugunList, getDongList };
