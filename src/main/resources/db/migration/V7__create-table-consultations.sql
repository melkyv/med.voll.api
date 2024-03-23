create table consultations (
    id bigint not null auto_increment,
    medic_id bigint not null,
    patient_id bigint not null,
    date_consultation datetime not null,

    primary key(id),
    constraint fk_consultation_medic_id foreign key(medic_id) references medics(id),
    constraint fk_consultation_patient_id foreign key(patient_id) references patients(id)
);