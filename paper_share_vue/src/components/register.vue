<template>
  <div>
    <navbar></navbar>
    <v-card class="mx-auto justify-center registerCard">
      <v-layout justify-center>
        <table>
          <th class="cardTitle">Sign Up</th>
          <tr>
            <td>
              <v-text-field v-model="user.username" label="Username" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-text-field v-model="user.firstName" label="FirstName" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-text-field v-model="user.lastName" label="LastName" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-text-field v-model="user.password" label="Password" :type="'password'" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-text-field
                v-model="user.repeatPassword"
                label="Repeat Password"
                :type="'password'"
                required
              ></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-text-field v-model="user.email" label="Email" required></v-text-field>
            </td>
          </tr>
          <tr>
            <td>
              <v-select v-model="user.title" :items="titles" label="Title"></v-select>
            </td>
          </tr>
          <tr>
            <td>
              <v-btn large color="primary">Register</v-btn>
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
  name: "register.vue",
  components: {
    navbar
  },
  data: function() {
    return {
      user: {},
      titles: ["BcS","McS","PhD"]
    };
  },
  methods: {
    register: function() {
      if (this.user.username === "" || this.user.username == null) {
        alert("Please enter username.");
        return;
      }
      if (this.user.password === "" || this.user.password == null) {
        alert("Please enter password.");
        return;
      }
      if (this.user.firstName === "" || this.user.firstName == null) {
        alert("Please enter first name.");
        return;
      }
      if (this.user.lastName === "" || this.user.lastName == null) {
        alert("Please enter last name.");
        return;
      }
      if (this.user.repeatPassword === "" || this.user.repeatPassword == null) {
        alert("Please repeat password.");
        return;
      }
      if (this.user.password !== this.user.repeatPassword) {
        alert("Repeat password is incorrect.");
        return;
      }
      if (this.user.email === "" || this.user.email == null) {
        alert("Please enter email.");
        return;
      }

      this.axios
        .post("http://localhost:8080/api/user/register", this.user)
        .then(response => {
          alert(response.data);
        });
    }
  }
};
</script>

<style scoped>
.registerCard {
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