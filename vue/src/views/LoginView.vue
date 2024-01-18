<template>
  <div id="login">
    <form @submit.prevent="login">
      <h1>Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <InputText id="username" type="text" v-model="user.username" Placeholder="Username" required autofocus />
      
        <Password id="password" v-model="user.password" required feedback={false} toggleMask placeholder="Password" />

      </div>
      <Button icon="pi pi-check" label="Sign In" aria-label="Sign In" />
      <p>
        <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>
</template>

<script>
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import Button from 'primevue/button';
import authService from "../services/AuthService";

export default {
  components: {
    InputText,
    Password,
    Button
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem; 
}
#password{
  margin-left: 0.5rem;
}
</style>