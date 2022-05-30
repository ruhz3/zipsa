<template>
  <div>
    <div id="menu-wrap">
      <div class="option">
        <b-row class="ml-2">
          <b-col cols="3" align="left">
            <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-form-select v-model="dongCode" :options="dongs" @change="searchApt"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-button variant="warning" @click="sendKeyword"><b-icon-search /></b-button>
            <!--<b-button variant="warning" @click="sendKeyword" class="ml-1"><b-icon-filter /></b-button>-->
          </b-col>
        </b-row>
      </div>
    </div>
    <div class="bg_white">
      <div class="option">
        <b-row class="ml-2">
          <b-col cols="3" align="left">
            <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-form-select v-model="dongCode" :options="dongs" @change="searchApt"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-button variant="warning" @click="sendKeyword"><b-icon-search /></b-button>
            <!--<b-button variant="warning" @click="sendKeyword" class="ml-1"><b-icon-filter /></b-button>-->
          </b-col>
        </b-row>
      </div>
    </div>
  </div>
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
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
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
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      console.log(this.dongCode);
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
  },
};
</script>
<style>
#menu-wrap {
  position: absolute;
  top: 300px;
  left: 20px;
  width: 550px;
  padding: 10px 10px;
  background: white;
  z-index: 1;
  font-size: 18px;
  border-radius: 10px;
}
</style>
