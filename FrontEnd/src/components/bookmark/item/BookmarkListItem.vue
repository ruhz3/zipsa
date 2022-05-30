<template>
  <b-card style="margin: 0px 50px 50px 0px; width: 350px; display: inline-block">
    <h2>
      <span>{{ aptName }}</span>
      &nbsp;&nbsp;<span @click="removeBookmark" class="btn sm">
        <b-icon-heart-fill></b-icon-heart-fill
      ></span>
    </h2>
    <table class="my-3">
      <tr>
        <th>준공년도</th>
        <td>&nbsp;&nbsp; {{ buildYear }}</td>
      </tr>
      <tr>
        <th>주소</th>
        <td>&nbsp;&nbsp; {{ dongName }} &nbsp;&nbsp; {{ jibun }}</td>
      </tr>
    </table>
    <div>
      <b-button id="show-btn" variant="outline-dark" class="w-100" @click="showHouseDeal()"
        >거래내역</b-button
      >
      <b-modal ok-variant="warning" ok-only ok-title="닫기" title="거래 내역" v-model="modalShow">
        <h2 class="underline-illuminating ml-2">{{ aptName }}</h2>
        <div class="modal-body" style="height: 600px; overflow-y: scroll">
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col" style="background-color: #999a9c; color: white">번호</th>
                <th scope="col" style="background-color: #999a9c; color: white">거래금액</th>
                <th scope="col" style="background-color: #999a9c; color: white">거래일시</th>
                <th scope="col" style="background-color: #999a9c; color: white">전용면적</th>
                <th scope="col" style="background-color: #999a9c; color: white">층</th>
              </tr>
            </thead>
            <tbody id="aptDetailList">
              <tr v-for="(houseDeal, i) in houseDealList" :key="houseDeal.no" v-bind="houseDeal">
                <td>{{ i + 1 }}</td>
                <td>{{ houseDeal.dealAmount }}만원</td>
                <td>{{ houseDeal.dealYear }}년 {{ houseDeal.dealMonth }}월</td>
                <td>{{ houseDeal.area }}㎡</td>
                <td>{{ houseDeal.floor }}층</td>
              </tr>
            </tbody>
          </table>
        </div>
      </b-modal>
    </div>
  </b-card>
</template>

<script>
import { deleteBookmark } from "@/api/bookmark";
import { houseDealList } from "@/api/house";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "BookmarkListItem",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  data() {
    return {
      modalShow: false,
      houseDealList: [],
    };
  },
  props: {
    aptCode: Number,
    aptName: String,
    dongCode: String,
    dongName: String,
    buildYear: Number,
    jibun: String,
    recentPrice: String,
  },
  methods: {
    removeBookmark() {
      deleteBookmark(
        this.userInfo.userId,
        this.aptCode,
        () => {
          this.$router.go();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    showHouseDeal() {
      this.modalShow = !this.modalShow;
      const params = { aptCode: this.aptCode };
      houseDealList(
        params,
        (response) => {
          console.log(response);
          this.houseDealList = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>
