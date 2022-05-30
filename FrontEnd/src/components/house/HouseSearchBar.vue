<template>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-col class="sm-3">
      <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="gugunCode" :options="guguns" @change="searchApt"></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
  methods: {
    ...mapActions("deal", [
      "setType",
      "setPay",
      "setSidoCode",
      "setGunguCode",
      "setDong",
      "getAptDealList",
      "getAptRentList",
      "getHomeDealList",
      "getHomeRentList",
      "getOfficeDealList",
      "getOfficeRentList",
    ]),
    ...mapGetters("deal", ["getType", "getPay", "getSidoCode", "getGunguCode", "getDong"]),
    sendKeyword() {
      if (this.sidoCode == "" || this.gunguCode == "" || this.dong == "") {
        alert("도/군/동을 입력해주세요");
        return;
      }
      if (this.type == "") {
        this.type = "apart";
      }
      if (this.pay == "") {
        this.pay = "deal";
      }
      /* 아파트 */
      if (this.sidoCode && this.type == "apart" && this.pay == "deal") {
        // 매매
        this.paramList = [this.gunguCode, this.dong, 1];
        this.getAptDealList(this.paramList);
      } else if (this.sidoCode && this.type == "apart" && this.pay == "rent") {
        // 월세
        this.paramList = [1, this.gunguCode, this.dong, 1];
        this.getAptRentList(this.paramList);
      } else if (this.sidoCode && this.type == "apart" && this.pay == "deposit") {
        // 전세
        this.paramList = [2, this.gunguCode, this.dong, 1];
        this.getAptRentList(this.paramList);
      } else if (this.sidoCode && this.type == "house" && this.pay == "deal") {
        /* 연립 다세대 */
        // 매매
        this.paramList = [this.gunguCode, this.dong, 1];
        this.getHomeDealList(this.paramList);
      } else if (this.sidoCode && this.type == "house" && this.pay == "rent") {
        // 월세
        this.paramList = [1, this.gunguCode, this.dong, 1];
        this.getHomeRentList(this.paramList);
      } else if (this.sidoCode && this.type == "house" && this.pay == "deposit") {
        // 전세
        this.paramList = [2, this.gunguCode, this.dong, 1];
        this.getHomeRentList(this.paramList);
      } else if (this.sidoCode && this.type == "office" && this.pay == "deal") {
        /* 오피스텔 */
        // 매매
        this.paramList = [this.gunguCode, this.dong, 1];
        this.getOfficeDealList(this.paramList);
      } else if (this.sidoCode && this.type == "office" && this.pay == "rent") {
        // 월세
        this.paramList = [1, this.gunguCode, this.dong, 1];
        this.getOfficeRentList(this.paramList);
      } else if (this.sidoCode && this.type == "office" && this.pay == "deposit") {
        // 전세
        this.paramList = [2, this.gunguCode, this.dong, 1];
        this.getOfficeRentList(this.paramList);
      }
      if (this.$route.name == "Home") this.$router.push("/main/price");
      else this.$router.this.$router.go();
    },
    setHouseType: function (event) {
      var thisBtn = event.currentTarget;
      thisBtn.classList.add("active");
      let sibling = thisBtn.parentNode.firstChild;

      while (sibling) {
        if (sibling.nodeType === 1 && sibling !== thisBtn) {
          sibling.classList.remove("active");
        }
        sibling = sibling.nextSibling;
      }
      this.type = event.currentTarget.value;
      // store에 저장
      this.setType(this.type);
    },
    setHousePay: function (event) {
      var thisBtn = event.currentTarget;
      thisBtn.classList.add("active");
      let sibling = thisBtn.parentNode.firstChild;

      while (sibling) {
        if (sibling.nodeType === 1 && sibling !== thisBtn) {
          sibling.classList.remove("active");
        }
        sibling = sibling.nextSibling;
      }
      this.pay = event.currentTarget.value;

      // store에 저장
      this.setPay(this.pay);
    },
    setSido() {
      http
        .get("/apart/gu/" + this.sidoCode)
        .then(({ data }) => {
          this.gunguList = data;
          console.log(data);
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      // store에 저장
      this.setSidoCode(this.sidoCode);
    },
    setGungu() {
      http
        .get("/apart/dong/" + this.gunguCode)
        .then(({ data }) => {
          this.dongList = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      // store에 저장
      this.setGunguCode(this.gunguCode);
    },
    setDongData() {
      // store에 저장
      this.setDong(this.dong);
    },
  },
};
</script>

<style></style>
