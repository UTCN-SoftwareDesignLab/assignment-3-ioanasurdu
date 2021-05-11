<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "New consultation" : "Edit consultation" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model="consultation.patient" label="Patient" />
                    <v-text-field v-model="consultation.user" label="Doctor"/>
                    <v-text-field v-model="consultation.date" label="Date" />
                    <v-text-field v-model="consultation.time" label="Time" />
                    <v-text-field v-model="consultation.type" label="Type" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteConsultation">Delete consultation</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "ConsultationDialog",
        props: {
            consultation: Object,
            opened: Boolean,
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.consultations
                        .create({
                            patient: this.consultation.patient,
                            user: this.consultation.user,
                            date: this.consultation.date,
                            time: this.consultation.time,
                            type: this.consultation.type,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.consultations
                        .edit({
                            id: this.consultation.id,
                            patient: this.consultation.patient,
                            user: this.consultation.user,
                            date: this.consultation.date,
                            time: this.consultation.time,
                            type: this.consultation.type,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            deleteConsultation(){
                api.consultations.delete({
                    id: this.consultation.id,
                }).then(() => this.$emit("refresh"));
            },
        },
        computed: {
            isNew: function () {
                return !this.consultation.id;
            },
        },
    };
</script>

<style scoped></style>
