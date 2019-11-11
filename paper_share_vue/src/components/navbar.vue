<template>
    <div  class="navbar" >
        <v-card   outlined>
                <v-row>
                     <v-col >
                        <img class="logoImg" width="280" height="100vh" style="background-color: silver" @click="$router.push('/')"/>
                    </v-col>
                    <v-col :cols="3" >
                        <v-row  class="navbarButtons">
                            <v-btn  class="ma-2" outlined color="indigo" text @click="$router.push('/login')">Log in</v-btn>
                            <v-btn  class="ma-2" outlined color="indigo" text @click="$router.push('/register')">Sign in</v-btn>
                        </v-row> 
                    </v-col>
                </v-row> 
        </v-card>
    </div>
</template>

<script>
    export default {
        name: "navbar"    
    }
</script>

<style scoped>
.navbar{
    height: 30vh;
}

.logoImg{
    margin-left:60px;
}

.navbarButtons{
    margin-top:60px;
}
</style>