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
        <b-button variant="warning" @click="moveModifyQna" class="mr-2">글수정</b-button>
        <b-button variant="dark" @click="removeQna">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-2">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${qnaInfoDto.title}</h3><div><h6>${qnaInfoDto.userId}</div><div>${qnaInfoDto.date}</h6></div>`"
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
    <b-list-group class="mb-5">
      <qna-comment-item
        v-for="qnaCommentInfoDto in qnaCommentInfoDtos"
        :key="qnaCommentInfoDto.commentid"
        v-bind="qnaCommentInfoDto"
      />
      <b-list-group-item v-if="userInfo && userInfo.isAdmin == 1">
        <b-row>
          <b-col cols="11">
            <b-form-input
              id="commentText"
              v-model="qnaCommentInputForm.content"
              type="text"
              style="height: 70px"
            ></b-form-input>
          </b-col>
          <b-col cols="1"><b-button @click="confirm" style="height: 70px">쓰기</b-button></b-col>
        </b-row>
      </b-list-group-item>
    </b-list-group>
  </div>
  <!--
  <b-container class="bv-example-row mt-3" style="height: 1000px">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="goBack">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-info" size="sm" @click="moveModifyQna" class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeQna">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${qnaInfoDto.qnaId}.
          ${qnaInfoDto.title}</h3><div><h6>${qnaInfoDto.userId}</div><div>${qnaInfoDto.date}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-list-group>
      <qna-comment-item
        v-for="qnaCommentInfoDto in qnaCommentInfoDtos"
        :key="qnaCommentInfoDto.commentid"
        v-bind="qnaCommentInfoDto"
      />
      <b-list-group-item v-if="userInfo && userInfo.isAdmin == 1">
        <b-row>
          <b-col cols="11">
            <b-form-input
              id="commentText"
              v-model="qnaCommentInputForm.content"
              type="text"
              style="height: 70px"
            ></b-form-input>
          </b-col>
          <b-col cols="1"><b-button @click="confirm" style="height: 70px">쓰기</b-button></b-col>
        </b-row>
      </b-list-group-item>
    </b-list-group>
  </b-container>
--></template>

<script>
// import moment from "moment";
import { getQna, deleteQna, getQnaCommentList, writeQnaComment } from "@/api/qna";
import QnaCommentItem from "@/components/support/item/QnaCommentItem";
import { mapState } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "QnaDetail",
  components: {
    QnaCommentItem,
  },
  data() {
    return {
      qnaInfoDto: {},
      qnaCommentInfoDtos: [],
      qnaCommentInputForm: {
        userId: "admin",
        qnaId: this.$route.params.qnaId,
        content: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    message() {
      if (this.qnaInfoDto.content) return this.qnaInfoDto.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQna(
      this.$route.params.qnaId,
      (response) => {
        this.qnaInfoDto = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
    getQnaCommentList(
      this.$route.params.qnaId,
      (response) => {
        console.log(response.data);
        this.qnaCommentInfoDtos = response.data;
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
    moveModifyQna() {
      this.$router.replace({
        name: "qnaModify",
        params: { qnaId: this.qnaInfoDto.qnaId },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeQna() {
      if (confirm("삭제하시겠습니까?")) {
        console.log(this.qnaInfoDto);
        deleteQna(
          this.qnaInfoDto.qnaId,
          (response) => {
            console.log(response);
            this.$router.push({ name: "support" });
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
    confirm() {
      if (this.qnaCommentInputForm.content == "") {
        alert("댓글 내용을 입력해주세요!");
      } else {
        console.log(this.qnaCommentInputForm);
        writeQnaComment(
          this.qnaCommentInputForm,
          () => {
            getQnaCommentList(
              this.$route.params.qnaId,
              (response) => {
                console.log(response.data);
                this.qnaCommentInfoDtos = response.data;
              },
              (error) => {
                console.log(error);
              },
            );
            document.getElementById("commentText").value = "";
          },
          (error) => {
            console.log(error);
          },
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
