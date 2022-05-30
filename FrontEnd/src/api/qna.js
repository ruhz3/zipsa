import { apiInstance } from "./index.js";

const api = apiInstance();

function listQna(success, fail) {
  api.get(`/qna`).then(success).catch(fail);
}
function listRecentQna(success, fail) {
  api.get(`/qna/recent`).then(success).catch(fail);
}

function getQna(qnaId, success, fail) {
  api.get(`/qna/${qnaId}`).then(success).catch(fail);
}

function deleteQna(qnaId, success, fail) {
  api.delete(`/qna/${qnaId}`).then(success).catch(fail);
}

function writeQna(qnaInfoDto, success, fail) {
  api.post(`/qna`, JSON.stringify(qnaInfoDto)).then(success).catch(fail);
}
function modifyQna(qnaInfoDto, success, fail) {
  api.put(`/qna/${qnaInfoDto.qnaId}`, JSON.stringify(qnaInfoDto)).then(success).catch(fail);
}

function getQnaCommentList(qnaId, success, fail) {
  api.get(`/qna/comments/${qnaId}`).then(success).catch(fail);
}
function writeQnaComment(qnaCommentInfoDto, success, fail) {
  api.post(`/qna/comments`, JSON.stringify(qnaCommentInfoDto)).then(success).catch(fail);
}
function deleteQnaComment(commentId, success, fail) {
  api.delete(`/qna/comments/${commentId}`).then(success).catch(fail);
}
export {
  listQna,
  listRecentQna,
  getQna,
  deleteQna,
  writeQna,
  modifyQna,
  getQnaCommentList,
  writeQnaComment,
  deleteQnaComment,
};
