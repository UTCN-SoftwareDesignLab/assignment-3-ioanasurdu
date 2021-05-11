<template>
    <v-card>
        <v-card-title>
            Consultations
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="addConsultation">New Consultation</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="consultations"
                :search="search"
                @click:row="editConsultation"
        ></v-data-table>
        <ConsultationDialog
                :opened="dialogVisible"
                :consultation="selectedItem"
                @refresh="refreshList"
        ></ConsultationDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import ConsultationDialog from "../components/ConsultationDialog";

    export default {
        name: "ConsultationList",
        components: { ConsultationDialog },
        data() {
            return {
                consultations: [],
                search: "",
                headers: [
                    {
                        text: "Patient",
                        align: "start",
                        sortable: false,
                        value: "patient.pcn",
                    },
                    { text: "Doctor", value: "user.username" },
                    { text: "Date", value: "date" },
                    { text: "Time", value: "time" },
                    { text: "Type", value: "type" },
                ],
                dialogVisible: false,
                selectedItem: {},
            };
        },
        methods: {
            editConsultation(consultation) {
                this.selectedItem = consultation;
                this.dialogVisible = true;
            },
            addConsultation() {
                this.dialogVisible = true;
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedItem = {};
                this.consultations = await api.consultations.allConsultations();
            },
        },
        created() {
            this.refreshList();
        },
    };
</script>

<style scoped></style>
