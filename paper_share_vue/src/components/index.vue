<template>
    <div>
        <navbar></navbar>
        
        <div style="text-align:center">
            <searchDialog></searchDialog>
            <!--
            <v-btn large color="blue-grey lighten-3" class="" style="">
                Search
                <v-icon right dark>search</v-icon>
            </v-btn>
            -->
        </div>
        
        <br>

        <v-card v-for="(paper, i) in this.papers" :key="i"   class="mx-auto paperInfo" >
            <v-row >
                <a href=""><h2>{{paper.title}}</h2></a>                  
            </v-row>
            <v-row>
                Authors: {{paper.authors}}
            </v-row>
            <v-row>
                {{paper.date}}
            </v-row>
            <v-card-text justify="auto">
                {{paper.description}}
            </v-card-text >
            <v-row>
               <i> {{paper.keywords}} </i>
            </v-row>
        </v-card>
    </div>
</template>

<script>
import navbar from "./navbar.vue"
import searchDialog from "./searchDialog.vue"
export default {
     name: "index",
        components: {
            navbar: navbar,
            searchDialog: searchDialog
        },
        data(){
        return {
            papers: [
                { title: "Etika u razvoju vestacke inteligencije", date: "11.6.2019.", authors: "MIle Prastalo, Jelena Cuk, Nikola Saric", keywords: "veštačka inteligencija, etika, bezbednost, privatnost, nezaposlenost",  description: "Glavni zadatak ovog rada je da upozna osobe koje ranije nisu imale kontakt sa veštačkom inteligencijom sa problemima koje ona donosi i koje bi mogla doneti."},
                { title: "Pregled problema koji jos uvek ne mogu da se rese dubokim ucenjem", date: "11.6.2019.", authors: "Uros Ogrizovic, Marko Simanic",  keywords: "duboko učcenje (Deep Learning, DL), konvolucione neuronske mrežze (Convolutional Neural Networks, CNN), mašsinsko učcenje (Machine Learning, ML)",  description: "Poslednjih godina, duboko uˇcenje (Deep Learning, DL) je postiglo veliki uspeh u mnogim oblastima. Medutim, postoji mnogo oblasti u kojima joˇs uvek ne daje zadovoljavaju´ce rezultate."}
            ]
        }
        }
}
</script>

<style scoped>
.paperInfo {
    border: solid 1px #5C6BC0 ;
      box-shadow:  0 0 10px  rgba(0,0,0,0.6);
      -moz-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      -webkit-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      -o-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      width: 700px; margin-bottom: 15px;
      padding: 2%;
      width: 60%;
}
</style>
