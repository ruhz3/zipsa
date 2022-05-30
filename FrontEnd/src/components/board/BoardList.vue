<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      커뮤니티
    </div>
    <div class="mb-2">
      <span class="h2">이 동네 살기 어때요?</span>
      <b-button class="ml-5" @click="moveWrite()" style="float: right">글쓰기</b-button>
    </div>
    <div class="mb-5">
      <b-table-simple
        id="article-table"
        :hover="true"
        :outlined="true"
        :head-variant="'light'"
        :per-page="perPage"
        :current-page="currentPage"
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
          <board-list-item v-for="article in articles" :key="article.articleno" v-bind="article" />
        </tbody>
      </b-table-simple>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        align="center"
        aria-controls="article-table"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import { listArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      perPage: 20,
      currentPage: 1,
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
