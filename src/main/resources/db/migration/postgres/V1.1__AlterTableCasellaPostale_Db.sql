ALTER TABLE webmail.casella_postale
    ADD COLUMN casella_attiva boolean NOT NULL DEFAULT True;

COMMENT ON COLUMN webmail.casella_postale.casella_attiva
    IS 'Indica se la casella è attiva o meno
Valore di default true';