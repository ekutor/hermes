package com.co.iatech.crm.sugarmovil.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de un contacto.
 */
public class ContactoDetalle extends GenericBean implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContactoDetalle> CREATOR = new Parcelable.Creator<ContactoDetalle>() {
        @Override
        public ContactoDetalle createFromParcel(Parcel in) {
            return new ContactoDetalle(in);
        }

        @Override
        public ContactoDetalle[] newArray(int size) {
            return new ContactoDetalle[size];
        }
    };
    private String id;
    private String date_entered;
    private String date_modified;
    private String modified_user_id;
    private String created_by;
    private String description;
    private String deleted;
    private String assigned_user_id;
    private String salutation;
    private String first_name;
    private String last_name;
    private String title;
    private String department;
    private String do_not_call;
    private String phone_home;
    private String phone_mobile;
    private String phone_work;
    private String phone_other;
    private String phone_fax;
    private String primary_address_street;
    private String primary_address_city;
    private String primary_address_state;
    private String primary_address_postalcode;
    private String primary_address_country;
    private String alt_address_street;
    private String alt_address_city;
    private String alt_address_state;
    private String alt_address_postalcode;
    private String alt_address_country;
    private String assistant;
    private String assistant_phone;
    private String lead_source;
    private String reports_to_id;
    private String birthdate;
    private String campaign_id;
    private String id_c;
    private String genero_c;
    private String profesion_c;
    private String certificaciones_c;
    private String tipocontacto_c;
    private String departamento_c;
    private String regalo1_c;
    private String regalo2_c;
    private String regalo3_c;
    private String regalo4_c;
    private String regalo5_c;
    private String mregalo1_c;
    private String mregalo2_c;
    private String mregalo3_c;
    private String mregalo4_c;
    private String mregalo5_c;
    private String fecregalo1_c;
    private String fecregalo2_c;
    private String fecregalo3_c;
    private String fecregalo4_c;
    private String fecregalo5_c;
    private String segmento_c;
    private String uen_c;
    private String canal_c;
    private String grupo_objetivo_c;
    private String estado_cliente_c;
    private String direccion_c;
    private String zona_c;
    private String extension1_c;
    private String extension2_c;
    private String identificacion_c;
    private String user_id_c;
    private String sector_c;
    private String municipio_c;
    private String email_address;
    private String idAccount;
    private String nameAccount;
    private String reports_to_name;
    private String created_by_name;
    private String modified_user_name;
    private String assigned_user_name;
    private String nameCampaign;

    public ContactoDetalle(JSONObject obj) throws JSONException {
 
    	 setId(validate(obj.getString("id")));
         setDate_entered(validate(obj.getString("date_entered")));
         setDate_modified(validate(obj.getString("date_modified")));
         setModified_user_id(validate(obj.getString("modified_user_id")));
         setCreated_by(validate(obj.getString("created_by")));
         setDescription(validate(obj.getString("description")));
         setDeleted(validate(obj.getString("deleted")));
         setAssigned_user_id(validate(obj.getString("assigned_user_id")));
         setSalutation(validate(obj.getString("salutation")));
         setFirst_name(validate(obj.getString("first_name")));
         setLast_name(validate(obj.getString("last_name")));
         setTitle(validate(obj.getString("title")));
         setDepartamento_c(validate(obj.getString("department")));
         setDo_not_call(validate(obj.getString("do_not_call")));
         setPhone_home(validate(obj.getString("phone_home")));
         setPhone_mobile(validate(obj.getString("phone_mobile")));
         setPhone_work(validate(obj.getString("phone_work")));
         setPhone_other(validate(obj.getString("phone_other")));
         setPhone_fax(validate(obj.getString("phone_fax")));
         setPrimary_address_street(validate(obj.getString("primary_address_street")));
         setPrimary_address_city(validate(obj.getString("primary_address_city")));
         setPrimary_address_state(validate(obj.getString("primary_address_state")));
         setPrimary_address_postalcode(validate(obj.getString("primary_address_postalcode")));
         setPrimary_address_country(validate(obj.getString("primary_address_country")));
         setAlt_address_state(validate(obj.getString("alt_address_street")));
         setAlt_address_city(validate(obj.getString("alt_address_city")));
         setAlt_address_state(validate(obj.getString("alt_address_state")));
         setAlt_address_postalcode(validate(obj.getString("alt_address_postalcode")));
         setAlt_address_country(validate(obj.getString("alt_address_country")));
         setAssistant(validate(obj.getString("assistant")));
         setAssistant_phone(validate(obj.getString("assistant_phone")));
         setLead_source(validate(obj.getString("lead_source")));
         setReports_to_id(validate(obj.getString("reports_to_id")));
         setBirthdate(validate(obj.getString("birthdate")));
         setCampaign_id(validate(obj.getString("campaign_id")));
         setId_c(validate(obj.getString("id_c")));
         setGenero_c(validate(obj.getString("genero_c")));
         setProfesion_c(validate(obj.getString("profesion_c")));
         setCertificaciones_c(validate(obj.getString("certificaciones_c")));
         setTipocontacto_c(validate(obj.getString("tipocontacto_c")));
         setDepartamento_c(validate(obj.getString("departamento_c")));
         setRegalo1_c(validate(obj.getString("regalo1_c")));
         setRegalo2_c(validate(obj.getString("regalo2_c")));
         setRegalo3_c(validate(obj.getString("regalo3_c")));
         setRegalo4_c(validate(obj.getString("regalo4_c")));
         setRegalo5_c(validate(obj.getString("regalo5_c")));
         setMregalo1_c(validate(obj.getString("mregalo1_c")));
         setMregalo2_c(validate(obj.getString("mregalo2_c")));
         setMregalo3_c(validate(obj.getString("mregalo3_c")));
         setMregalo4_c(validate(obj.getString("mregalo4_c")));
         setMregalo5_c(validate(obj.getString("mregalo5_c")));
         setFecregalo1_c(validate(obj.getString("fecregalo1_c")));
         setFecregalo2_c(validate(obj.getString("fecregalo2_c")));
         setFecregalo3_c(validate(obj.getString("fecregalo3_c")));
         setFecregalo4_c(validate(obj.getString("fecregalo4_c")));
         setFecregalo5_c(validate(obj.getString("fecregalo5_c")));
         setSegmento_c(validate(obj.getString("segmento_c")));
         setUen_c(validate(obj.getString("uen_c")));
         setCanal_c(validate(obj.getString("canal_c")));
         setGrupo_objetivo_c(validate(obj.getString("grupo_objetivo_c")));
         setEstado_cliente_c(validate(obj.getString("estado_cliente_c")));
         setDireccion_c(validate(obj.getString("direccion_c")));
         setZona_c(validate(obj.getString("zona_c")));
         setExtension1_c(validate(obj.getString("extension1_c")));
         setExtension2_c(validate(obj.getString("extension2_c")));
         setIdentificacion_c(validate(obj.getString("identificacion_c")));
         setUser_id_c(validate(obj.getString("user_id_c")));
         setSector_c(validate(obj.getString("sector_c")));
         setMunicipio_c(validate(obj.getString("municipio_c")));
         setEmail_address(validate(obj.getString("email_address")));
         setIdAccount(validate(obj.getString("idAccount")));
         setNameAccount(validate(obj.getString("nameAccount")));
         setReports_to_name(validate(obj.getString("reports_to_name")));
         setCreated_by_name(validate(obj.getString("created_by_name")));
         setModified_user_name(validate(obj.getString("modified_user_name")));
         setAssigned_user_name(validate(obj.getString("assigned_user_name")));
         setNameCampaign(validate(obj.getString("nameCampaign")));
       
    }

    protected ContactoDetalle(Parcel in) {
    	 id = validate(in.readString());
         date_entered = validate(in.readString());
         date_modified = validate(in.readString());
         modified_user_id = validate(in.readString());
         created_by = validate(in.readString());
         description = validate(in.readString());
         deleted = validate(in.readString());
         assigned_user_id = validate(in.readString());
         salutation = validate(in.readString());
         first_name = validate(in.readString());
         last_name = validate(in.readString());
         title = validate(in.readString());
         department = validate(in.readString());
         do_not_call = validate(in.readString());
         phone_home = validate(in.readString());
         phone_mobile = validate(in.readString());
         phone_work = validate(in.readString());
         phone_other = validate(in.readString());
         phone_fax = validate(in.readString());
         primary_address_street = validate(in.readString());
         primary_address_city = validate(in.readString());
         primary_address_state = validate(in.readString());
         primary_address_postalcode = validate(in.readString());
         primary_address_country = validate(in.readString());
         alt_address_street = validate(in.readString());
         alt_address_city = validate(in.readString());
         alt_address_state = validate(in.readString());
         alt_address_postalcode = validate(in.readString());
         alt_address_country = validate(in.readString());
         assistant = validate(in.readString());
         assistant_phone = validate(in.readString());
         lead_source = validate(in.readString());
         reports_to_id = validate(in.readString());
         birthdate = validate(in.readString());
         campaign_id = validate(in.readString());
         id_c = validate(in.readString());
         genero_c = validate(in.readString());
         profesion_c = validate(in.readString());
         certificaciones_c = validate(in.readString());
         tipocontacto_c = validate(in.readString());
         departamento_c = validate(in.readString());
         regalo1_c = validate(in.readString());
         regalo2_c = validate(in.readString());
         regalo3_c = validate(in.readString());
         regalo4_c = validate(in.readString());
         regalo5_c = validate(in.readString());
         mregalo1_c = validate(in.readString());
         mregalo2_c = validate(in.readString());
         mregalo3_c = validate(in.readString());
         mregalo4_c = validate(in.readString());
         mregalo5_c = validate(in.readString());
         fecregalo1_c = validate(in.readString());
         fecregalo2_c = validate(in.readString());
         fecregalo3_c = validate(in.readString());
         fecregalo4_c = validate(in.readString());
         fecregalo5_c = validate(in.readString());
         segmento_c = validate(in.readString());
         uen_c = validate(in.readString());
         canal_c = validate(in.readString());
         grupo_objetivo_c = validate(in.readString());
         estado_cliente_c = validate(in.readString());
         direccion_c = validate(in.readString());
         zona_c = validate(in.readString());
         extension1_c = validate(in.readString());
         extension2_c = validate(in.readString());
         identificacion_c = validate(in.readString());
         user_id_c = validate(in.readString());
         sector_c = validate(in.readString());
         municipio_c = validate(in.readString());
         email_address = validate(in.readString());
         idAccount = validate(in.readString());
         nameAccount = validate(in.readString());
         reports_to_name = validate(in.readString());
         created_by_name = validate(in.readString());
         modified_user_name = validate(in.readString());
         assigned_user_name = validate(in.readString());
         nameCampaign = validate(in.readString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_entered() {
        return date_entered;
    }

    public void setDate_entered(String date_entered) {
        this.date_entered = date_entered;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getModified_user_id() {
        return modified_user_id;
    }

    public void setModified_user_id(String modified_user_id) {
        this.modified_user_id = modified_user_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getAssigned_user_id() {
        return assigned_user_id;
    }

    public void setAssigned_user_id(String assigned_user_id) {
        this.assigned_user_id = assigned_user_id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDo_not_call() {
        return do_not_call;
    }

    public void setDo_not_call(String do_not_call) {
        this.do_not_call = do_not_call;
    }

    public String getPhone_home() {
        return phone_home;
    }

    public void setPhone_home(String phone_home) {
        this.phone_home = phone_home;
    }

    public String getPhone_mobile() {
        return phone_mobile;
    }

    public void setPhone_mobile(String phone_mobile) {
        this.phone_mobile = phone_mobile;
    }

    public String getPhone_work() {
        return phone_work;
    }

    public void setPhone_work(String phone_work) {
        this.phone_work = phone_work;
    }

    public String getPhone_other() {
        return phone_other;
    }

    public void setPhone_other(String phone_other) {
        this.phone_other = phone_other;
    }

    public String getPhone_fax() {
        return phone_fax;
    }

    public void setPhone_fax(String phone_fax) {
        this.phone_fax = phone_fax;
    }

    public String getPrimary_address_street() {
        return primary_address_street;
    }

    public void setPrimary_address_street(String primary_address_street) {
        this.primary_address_street = primary_address_street;
    }

    public String getPrimary_address_city() {
        return primary_address_city;
    }

    public void setPrimary_address_city(String primary_address_city) {
        this.primary_address_city = primary_address_city;
    }

    public String getPrimary_address_state() {
        return primary_address_state;
    }

    public void setPrimary_address_state(String primary_address_state) {
        this.primary_address_state = primary_address_state;
    }

    public String getPrimary_address_postalcode() {
        return primary_address_postalcode;
    }

    public void setPrimary_address_postalcode(String primary_address_postalcode) {
        this.primary_address_postalcode = primary_address_postalcode;
    }

    public String getPrimary_address_country() {
        return primary_address_country;
    }

    public void setPrimary_address_country(String primary_address_country) {
        this.primary_address_country = primary_address_country;
    }

    public String getAlt_address_street() {
        return alt_address_street;
    }

    public void setAlt_address_street(String alt_address_street) {
        this.alt_address_street = alt_address_street;
    }

    public String getAlt_address_city() {
        return alt_address_city;
    }

    public void setAlt_address_city(String alt_address_city) {
        this.alt_address_city = alt_address_city;
    }

    public String getAlt_address_state() {
        return alt_address_state;
    }

    public void setAlt_address_state(String alt_address_state) {
        this.alt_address_state = alt_address_state;
    }

    public String getAlt_address_postalcode() {
        return alt_address_postalcode;
    }

    public void setAlt_address_postalcode(String alt_address_postalcode) {
        this.alt_address_postalcode = alt_address_postalcode;
    }

    public String getAlt_address_country() {
        return alt_address_country;
    }

    public void setAlt_address_country(String alt_address_country) {
        this.alt_address_country = alt_address_country;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getAssistant_phone() {
        return assistant_phone;
    }

    public void setAssistant_phone(String assistant_phone) {
        this.assistant_phone = assistant_phone;
    }

    public String getLead_source() {
        return lead_source;
    }

    public void setLead_source(String lead_source) {
        this.lead_source = lead_source;
    }

    public String getReports_to_id() {
        return reports_to_id;
    }

    public void setReports_to_id(String reports_to_id) {
        this.reports_to_id = reports_to_id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getGenero_c() {
        return genero_c;
    }

    public void setGenero_c(String genero_c) {
        this.genero_c = genero_c;
    }

    public String getProfesion_c() {
        return profesion_c;
    }

    public void setProfesion_c(String profesion_c) {
        this.profesion_c = profesion_c;
    }

    public String getCertificaciones_c() {
        return certificaciones_c;
    }

    public void setCertificaciones_c(String certificaciones_c) {
        this.certificaciones_c = certificaciones_c;
    }

    public String getTipocontacto_c() {
        return tipocontacto_c;
    }

    public void setTipocontacto_c(String tipocontacto_c) {
        this.tipocontacto_c = tipocontacto_c;
    }

    public String getDepartamento_c() {
        return departamento_c;
    }

    public void setDepartamento_c(String departamento_c) {
        this.departamento_c = departamento_c;
    }

    public String getRegalo1_c() {
        return regalo1_c;
    }

    public void setRegalo1_c(String regalo1_c) {
        this.regalo1_c = regalo1_c;
    }

    public String getRegalo2_c() {
        return regalo2_c;
    }

    public void setRegalo2_c(String regalo2_c) {
        this.regalo2_c = regalo2_c;
    }

    public String getRegalo3_c() {
        return regalo3_c;
    }

    public void setRegalo3_c(String regalo3_c) {
        this.regalo3_c = regalo3_c;
    }

    public String getRegalo4_c() {
        return regalo4_c;
    }

    public void setRegalo4_c(String regalo4_c) {
        this.regalo4_c = regalo4_c;
    }

    public String getRegalo5_c() {
        return regalo5_c;
    }

    public void setRegalo5_c(String regalo5_c) {
        this.regalo5_c = regalo5_c;
    }

    public String getMregalo1_c() {
        return mregalo1_c;
    }

    public void setMregalo1_c(String mregalo1_c) {
        this.mregalo1_c = mregalo1_c;
    }

    public String getMregalo2_c() {
        return mregalo2_c;
    }

    public void setMregalo2_c(String mregalo2_c) {
        this.mregalo2_c = mregalo2_c;
    }

    public String getMregalo3_c() {
        return mregalo3_c;
    }

    public void setMregalo3_c(String mregalo3_c) {
        this.mregalo3_c = mregalo3_c;
    }

    public String getMregalo4_c() {
        return mregalo4_c;
    }

    public void setMregalo4_c(String mregalo4_c) {
        this.mregalo4_c = mregalo4_c;
    }

    public String getMregalo5_c() {
        return mregalo5_c;
    }

    public void setMregalo5_c(String mregalo5_c) {
        this.mregalo5_c = mregalo5_c;
    }

    public String getFecregalo1_c() {
        return fecregalo1_c;
    }

    public void setFecregalo1_c(String fecregalo1_c) {
        this.fecregalo1_c = fecregalo1_c;
    }

    public String getFecregalo2_c() {
        return fecregalo2_c;
    }

    public void setFecregalo2_c(String fecregalo2_c) {
        this.fecregalo2_c = fecregalo2_c;
    }

    public String getFecregalo3_c() {
        return fecregalo3_c;
    }

    public void setFecregalo3_c(String fecregalo3_c) {
        this.fecregalo3_c = fecregalo3_c;
    }

    public String getFecregalo4_c() {
        return fecregalo4_c;
    }

    public void setFecregalo4_c(String fecregalo4_c) {
        this.fecregalo4_c = fecregalo4_c;
    }

    public String getFecregalo5_c() {
        return fecregalo5_c;
    }

    public void setFecregalo5_c(String fecregalo5_c) {
        this.fecregalo5_c = fecregalo5_c;
    }

    public String getSegmento_c() {
        return segmento_c;
    }

    public void setSegmento_c(String segmento_c) {
        this.segmento_c = segmento_c;
    }

    public String getUen_c() {
        return uen_c;
    }

    public void setUen_c(String uen_c) {
        this.uen_c = uen_c;
    }

    public String getCanal_c() {
        return canal_c;
    }

    public void setCanal_c(String canal_c) {
        this.canal_c = canal_c;
    }

    public String getGrupo_objetivo_c() {
        return grupo_objetivo_c;
    }

    public void setGrupo_objetivo_c(String grupo_objetivo_c) {
        this.grupo_objetivo_c = grupo_objetivo_c;
    }

    public String getEstado_cliente_c() {
        return estado_cliente_c;
    }

    public void setEstado_cliente_c(String estado_cliente_c) {
        this.estado_cliente_c = estado_cliente_c;
    }

    public String getDireccion_c() {
        return direccion_c;
    }

    public void setDireccion_c(String direccion_c) {
        this.direccion_c = direccion_c;
    }

    public String getZona_c() {
        return zona_c;
    }

    public void setZona_c(String zona_c) {
        this.zona_c = zona_c;
    }

    public String getExtension1_c() {
        return extension1_c;
    }

    public void setExtension1_c(String extension1_c) {
        this.extension1_c = extension1_c;
    }

    public String getExtension2_c() {
        return extension2_c;
    }

    public void setExtension2_c(String extension2_c) {
        this.extension2_c = extension2_c;
    }

    public String getIdentificacion_c() {
        return identificacion_c;
    }

    public void setIdentificacion_c(String identificacion_c) {
        this.identificacion_c = identificacion_c;
    }

    public String getUser_id_c() {
        return user_id_c;
    }

    public void setUser_id_c(String user_id_c) {
        this.user_id_c = user_id_c;
    }

    public String getSector_c() {
        return sector_c;
    }

    public void setSector_c(String sector_c) {
        this.sector_c = sector_c;
    }

    public String getMunicipio_c() {
        return municipio_c;
    }

    public void setMunicipio_c(String municipio_c) {
        this.municipio_c = municipio_c;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getReports_to_name() {
        return reports_to_name;
    }

    public void setReports_to_name(String reports_to_name) {
        this.reports_to_name = reports_to_name;
    }

    public String getCreated_by_name() {
        return created_by_name;
    }

    public void setCreated_by_name(String created_by_name) {
        this.created_by_name = created_by_name;
    }

    public String getModified_user_name() {
        return modified_user_name;
    }

    public void setModified_user_name(String modified_user_name) {
        this.modified_user_name = modified_user_name;
    }

    public String getAssigned_user_name() {
        return assigned_user_name;
    }

    public void setAssigned_user_name(String assigned_user_name) {
        this.assigned_user_name = assigned_user_name;
    }

    public String getNameCampaign() {
        return nameCampaign;
    }

    public void setNameCampaign(String nameCampaign) {
        this.nameCampaign = nameCampaign;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(date_entered);
        dest.writeString(date_modified);
        dest.writeString(modified_user_id);
        dest.writeString(created_by);
        dest.writeString(description);
        dest.writeString(deleted);
        dest.writeString(assigned_user_id);
        dest.writeString(salutation);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(title);
        dest.writeString(department);
        dest.writeString(do_not_call);
        dest.writeString(phone_home);
        dest.writeString(phone_mobile);
        dest.writeString(phone_work);
        dest.writeString(phone_other);
        dest.writeString(phone_fax);
        dest.writeString(primary_address_street);
        dest.writeString(primary_address_city);
        dest.writeString(primary_address_state);
        dest.writeString(primary_address_postalcode);
        dest.writeString(primary_address_country);
        dest.writeString(alt_address_street);
        dest.writeString(alt_address_city);
        dest.writeString(alt_address_state);
        dest.writeString(alt_address_postalcode);
        dest.writeString(alt_address_country);
        dest.writeString(assistant);
        dest.writeString(assistant_phone);
        dest.writeString(lead_source);
        dest.writeString(reports_to_id);
        dest.writeString(birthdate);
        dest.writeString(campaign_id);
        dest.writeString(id_c);
        dest.writeString(genero_c);
        dest.writeString(profesion_c);
        dest.writeString(certificaciones_c);
        dest.writeString(tipocontacto_c);
        dest.writeString(departamento_c);
        dest.writeString(regalo1_c);
        dest.writeString(regalo2_c);
        dest.writeString(regalo3_c);
        dest.writeString(regalo4_c);
        dest.writeString(regalo5_c);
        dest.writeString(mregalo1_c);
        dest.writeString(mregalo2_c);
        dest.writeString(mregalo3_c);
        dest.writeString(mregalo4_c);
        dest.writeString(mregalo5_c);
        dest.writeString(fecregalo1_c);
        dest.writeString(fecregalo2_c);
        dest.writeString(fecregalo3_c);
        dest.writeString(fecregalo4_c);
        dest.writeString(fecregalo5_c);
        dest.writeString(segmento_c);
        dest.writeString(uen_c);
        dest.writeString(canal_c);
        dest.writeString(grupo_objetivo_c);
        dest.writeString(estado_cliente_c);
        dest.writeString(direccion_c);
        dest.writeString(zona_c);
        dest.writeString(extension1_c);
        dest.writeString(extension2_c);
        dest.writeString(identificacion_c);
        dest.writeString(user_id_c);
        dest.writeString(sector_c);
        dest.writeString(municipio_c);
        dest.writeString(email_address);
        dest.writeString(idAccount);
        dest.writeString(nameAccount);
        dest.writeString(reports_to_name);
        dest.writeString(created_by_name);
        dest.writeString(modified_user_name);
        dest.writeString(assigned_user_name);
        dest.writeString(nameCampaign);
    }
}