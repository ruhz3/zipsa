<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      고객센터
    </div>
    <!--고객센터 정보-->
    <div class="mb-5" style="font-size: x-large">
      <table>
        <tr>
          <th>전화번호</th>
          <td>&nbsp;&nbsp;010-1234-1234</td>
        </tr>
        <tr>
          <th>상담시간</th>
          <td>&nbsp;&nbsp;(평일) 09:00~18:00</td>
        </tr>
        <tr>
          <th>주소</th>
          <td>&nbsp;&nbsp;서울특별시 노원구 노원동 노원아파트</td>
        </tr>
        <tr>
          <td colspan="2"></td>
        </tr>
      </table>
    </div>
    <!--공지사항-->
    <div class="container-fluid d-flex">
      <div class="container-fluid mb-5">
        <div class="container-fluid mb-5">
          <div class="mb-5">
            <div class="mb-3">
              <span class="h2">공지사항</span>
              <span class="ml-4"
                ><router-link :to="{ name: 'noticeList' }" class="link"
                  >더보기<b-icon-arrow-right /></router-link
              ></span>
            </div>
            <b-table-simple
              id="notice-recent-table"
              :hover="true"
              :outlined="true"
              :head-variant="'light'"
              caption-top
            >
              <b-thead>
                <b-tr>
                  <b-th>번호</b-th>
                  <b-th>제목</b-th>
                  <b-th>작성일</b-th>
                  <b-th>작성자</b-th>
                </b-tr>
              </b-thead>
              <tbody>
                <notice-list-item
                  v-for="noticeInfoDto in noticeInfoDtos"
                  :key="noticeInfoDto.noticeId"
                  v-bind="noticeInfoDto"
                />
              </tbody>
            </b-table-simple>
          </div>
        </div>
      </div>
      <!--Q & A-->
      <div class="container-fluid mb-5">
        <div class="container-fluid mb-5">
          <div class="mb-5">
            <div class="mb-3">
              <span class="h2">Q & A</span>
              <span class="ml-4"
                ><router-link :to="{ name: 'qnaList' }" class="link"
                  >더보기<b-icon-arrow-right /></router-link
              ></span>
            </div>
            <b-table-simple
              id="notice-recent-table"
              :hover="true"
              :outlined="true"
              :head-variant="'light'"
              caption-top
            >
              <b-thead>
                <b-tr>
                  <b-th>번호</b-th>
                  <b-th>제목</b-th>
                  <b-th>작성일</b-th>
                  <b-th>작성자</b-th>
                </b-tr>
              </b-thead>
              <tbody>
                <qna-list-item
                  v-for="qnaInfoDto in qnaInfoDtos"
                  :key="qnaInfoDto.qnaId"
                  v-bind="qnaInfoDto"
                />
              </tbody>
            </b-table-simple>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { listRecentNotice } from "@/api/notice.js";
import { listRecentQna } from "@/api/qna.js";
import NoticeListItem from "@/components/support/item/NoticeListItem";
import QnaListItem from "@/components/support/item/QnaListItem";

export default {
  components: {
    NoticeListItem,
    QnaListItem,
  },
  data() {
    return {
      noticeInfoDtos: [],
      qnaInfoDtos: [],
    };
  },
  created() {
    listRecentNotice(
      (response) => {
        this.noticeInfoDtos = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
    listRecentQna(
      (response) => {
        this.qnaInfoDtos = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
};
</script>

<style></style>
