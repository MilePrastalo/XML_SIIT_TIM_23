<template>
  <div>
    <navbar></navbar>
    <v-card class="mx-auto justify-center loginCard">
      <v-layout justify-center>
        <table>
          <th class="cardTitle">Log In</th>
          <tr>
            <td>
              <v-text-field v-model="username" label="Username" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-text-field v-model="password" label="Password" :type="'password'" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-btn large color="primary">Log In</v-btn>
            </td>
          </tr>
        </table>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
import navbar from "./navbar.vue";

export default {
  name: "login.vue",
  components: {
    navbar
  },
  data: function() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    login: function() {
      if (this.username === "") {
        alert("Please enter username.");
        return;
      }
      if (this.password === "") {
        alert("Please enter password.");
        return;
      }
      this.axios
        .post("http://localhost:8080/api/user/login", {
          username: this.username,
          password: this.password
        })
        .then(response => {
          if (response.data.token !== "") {
            localStorage.setItem("jwt", response.data.token);
            alert("Successfully logged in.");
            this.$router.push("/");
          } else if (response.data.token === null) {
            alert("Username or password is incorrect.");
          }
        });
      //this.$forceUpdate();
      //window.location.reload();
    }
  }
};
</script>

<style scoped>
.loginCard {
  border: solid 1px #5c6bc0;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
  -moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
  -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
  -o-box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
  width: 400px;
  margin-bottom: 15px;
  padding: 2%;
  width: 30%;
}
.cardTitle {
  font-size: 45px;
}
</style>