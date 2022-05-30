<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <!--제목-->
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      커뮤니티
    </div>
    <!--정보 게시판-->
    <div class="container-fluid mb-5">
      <!--정보 게시판 제목-->
      <h1 class="mb-2"><b-icon-house-door-fill style="color: #f6e45e" /> 정보 게시판</h1>
      <hr />
      <!--정보 게시판 컨텐츠-->
      <div class="mt-5">
        <!--이 동네 살기 어때요? 컨텐츠-->
        <div class="mb-5">
          <div class="mb-3">
            <span class="h2">이 동네 살기 어때요?</span>
            <span class="ml-4"
              ><router-link :to="{ name: 'board' }" class="link"
                >더보기<b-icon-arrow-right /></router-link
            ></span>
          </div>
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
              <board-list-item
                v-for="article in articles"
                :key="article.articleno"
                v-bind="article"
              />
            </tbody>
          </b-table-simple>
        </div>
        <!--후기 게시판 컨텐츠-->
        <div>
          <div class="mb-3">
            <span class="h2">후기 게시판</span>
            <span class="ml-4"
              ><router-link :to="{ name: 'board' }" class="link"
                >더보기<b-icon-arrow-right /></router-link
            ></span>
          </div>
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
              <board-list-item
                v-for="article in articles"
                :key="article.articleno"
                v-bind="article"
              />
            </tbody>
          </b-table-simple>
        </div>
      </div>
    </div>
    <!--자유 게시판-->
    <div class="container-fluid pt-5">
      <!--자유 게시판 제목-->
      <h1 class="mb-1"><b-icon-people-fill style="color: #f6e45e" /> 자유 게시판</h1>
      <hr />
      <!--자유 게시판 컨텐츠-->
      <div class="mt-5">
        <div>
          <div class="mb-3">
            <span class="h2">랜선 집들이</span>
            <span class="ml-4"
              ><router-link :to="{ name: 'board' }" class="link"
                >더보기<b-icon-arrow-right /></router-link
            ></span>
          </div>
          <b-row class="mb-3">
            <board-gallery-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </b-row>
        </div>
      </div>
      <div class="mt-5">
        <div>
          <div class="mb-3">
            <span class="h2">랜선 집들이</span>
            <span class="ml-4"
              ><router-link :to="{ name: 'board' }" class="link"
                >더보기<b-icon-arrow-right /></router-link
            ></span>
          </div>
          <b-row class="mb-3">
            <board-gallery-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </b-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { listArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "CommunityHome",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
};
</script>

<style></style>
