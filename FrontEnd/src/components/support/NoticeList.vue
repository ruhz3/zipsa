<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      고객센터
    </div>
    <div class="mb-2">
      <span class="h2">공지사항</span>
      <b-button class="ml-5" @click="moveWrite()" style="float: right">글쓰기</b-button>
    </div>
    <div class="mb-5">
      <b-table-simple
        id="article-table"
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
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <notice-list-item
            v-for="noticeInfoDto in noticeInfoDtos"
            :key="noticeInfoDto.noticeId"
            v-bind="noticeInfoDto"
          />
        </tbody>
      </b-table-simple>
    </div>
  </div>
</template>

<script>
import { listNotice } from "@/api/notice.js";
import NoticeListItem from "@/components/support/item/NoticeListItem";

export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      noticeInfoDtos: [],
    };
  },
  created() {
    listNotice(
      (response) => {
        this.noticeInfoDtos = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeRegister" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
