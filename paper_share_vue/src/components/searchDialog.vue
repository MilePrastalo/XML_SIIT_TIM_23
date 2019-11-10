<template>
    <div>

    <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on }">
                        <v-btn large color="blue-grey lighten-3"  v-on="on" class="" style="">
                            Search
                            <v-icon right dark>search</v-icon>
                        </v-btn>
                    </template>
                    <v-card>
                    <v-card-title>
                        <span class="headline">Search and Filter</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            
                            <v-row>
                                <v-text-field v-model="title" shaped outlined label="Title*" required></v-text-field>
                                </v-row>
                                <v-row >
                                    <v-text-field v-model="authors" shaped outlined label="Authors*" required></v-text-field>
                                </v-row>
                                <v-row>
                                    <v-col cols="12" sm="6">
                                        <v-combobox v-model="subject"
                                            label="Subject*"
                                        ></v-combobox>
                                    </v-col>
                                    <v-col cols="12" sm="6" v-model="language">
                                        <v-combobox
                                            label="Language*"
                                        ></v-combobox>
                                    </v-col>
                                </v-row>
                                <v-row >
                                    <v-text-field type="date" v-model="date"></v-text-field>
                                </v-row>
                                <v-row>
                                    <v-text-field v-model="keywords" shaped outlined label="Keywords*" required></v-text-field>
                                </v-row>
                                <v-row>
                                    <v-textarea v-model="text" shaped outlined label="Text*" required></v-textarea>
                                </v-row>
                                    
                               
                        </v-container>
                    </v-card-text>
                    <v-card-actions class="justify-center">
                        <v-btn @click="closeQuestionDialog()" outlined color="indigo"  >Close</v-btn>
                        <v-btn @click="search()" color="indigo"  >Apply</v-btn>
                    </v-card-actions>
                </v-card>
    </v-dialog>
    </div>
</template>

<script>
  export default {
        name: "searchDialog",
        data(){
        return {
            dialog: false,
            authors: "",
            title: "",
            language: "",
            subject: "",
            date: "",
            keywords: "",
            text: ""
            }  
        },
        methods: {
            closeQuestionDialog:function(){
                this.dialog = false;
            },
        }
  }
</script>

<style>
</style>
