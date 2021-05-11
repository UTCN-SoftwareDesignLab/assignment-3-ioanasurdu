<template>
    <v-card>
        <v-card-title>
            Patients
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="addPatient">New Patient</v-btn>
            <v-btn @click="consultations">Consultations</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="patients"
                :search="search"
                @click:row="editPatient"
        ></v-data-table>
        <PatientDialog
                :opened="dialogVisible"
                :patient="selectedItem"
                @refresh="refreshList"
        ></PatientDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import PatientDialog from "../components/PatientDialog";
    import router from "@/router";

    export default {
        name: "PatientList",
        components: { PatientDialog },
        data() {
            return {
                patients: [],
                search: "",
                headers: [
                    {
                        text: "Name",
                        align: "start",
                        sortable: false,
                        value: "name",
                    },
                    { text: "ICN", value: "icn" },
                    { text: "PCN", value: "pcn" },
                    { text: "Date of birth", value: "dateOfBirth" },
                    { text: "Address", value: "address" },
                ],
                dialogVisible: false,
                selectedItem: {},
            };
        },
        methods: {
            editPatient(patient) {
                this.selectedItem = patient;
                this.dialogVisible = true;
            },
            addPatient() {
                this.dialogVisible = true;
            },
            consultations(){
                router.push("/consultation")
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedItem = {};
                this.patients = await api.patients.allPatients();
            },
        },
        created() {
            this.refreshList();
        },
    };
</script>

<style scoped></style>
