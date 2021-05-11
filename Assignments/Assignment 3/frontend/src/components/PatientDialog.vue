<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "New patient" : "Edit patient" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model="patient.name" label="Name" />
                    <v-text-field v-model="patient.icn" label="ICN" />
                    <v-text-field v-model="patient.pcn" label="PCN" />
                    <v-text-field v-model="patient.dateOfBirth" label="Date of birth" />
                    <v-text-field v-model="patient.address" label="Address" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "PatientDialog",
        props: {
            patient: Object,
            opened: Boolean,
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.patients
                        .create({
                            name: this.patient.name,
                            icn: this.patient.icn,
                            pcn: this.patient.pcn,
                            dateOfBirth: this.patient.dateOfBirth,
                            address: this.patient.address,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.patients
                        .edit({
                            id: this.patient.id,
                            name: this.patient.name,
                            icn: this.patient.icn,
                            pcn: this.patient.pcn,
                            dateOfBirth: this.patient.dateOfBirth,
                            address: this.patient.address,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
        },
        computed: {
            isNew: function () {
                return !this.patient.id;
            },
        },
    };
</script>

<style scoped></style>
