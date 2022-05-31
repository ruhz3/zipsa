<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      고객센터
    </div>
    <h1 class="mb-2">게시글</h1>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-dark" @click="goBack">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="warning" @click="moveModifyNotice" class="mr-2">글수정</b-button>
        <b-button variant="dark" @click="removeNotice">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-5">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${noticeInfoDto.title}</h3><div><h6>${noticeInfoDto.userId}</div><div>${noticeInfoDto.date}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div style="min-height: 350px" v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
// import moment from "moment";
import { getNotice, deleteNotice } from "@/api/notice";

export default {
  name: "NoticeDetail",
  data() {
    return {
      noticeInfoDto: {},
    };
  },
  computed: {
    message() {
      if (this.noticeInfoDto.content) return this.noticeInfoDto.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getNotice(
      this.$route.params.noticeId,
      (response) => {
        this.noticeInfoDto = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    goBack() {
      this.$router.push({ name: "support" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeId: this.noticeInfoDto.noticeId },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeNotice() {
      if (confirm("삭제하시겠습니까?")) {
        deleteNotice(
          this.noticeInfoDto.noticeId,
          () => {
            this.$router.push({ name: "support" });
          },
          () => {},
        );
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
