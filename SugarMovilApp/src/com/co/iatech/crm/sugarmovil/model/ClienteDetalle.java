package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los clientes.
 */
public class ClienteDetalle extends GenericBean implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ClienteDetalle> CREATOR = new Parcelable.Creator<ClienteDetalle>() {
        @Override
        public ClienteDetalle createFromParcel(Parcel in) {
            return new ClienteDetalle(in);
        }

        @Override
        public ClienteDetalle[] newArray(int size) {
            return new ClienteDetalle[size];
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
    private String converted;
    private String refered_by;
    private String lead_source;
    private String lead_source_description;
    private String status;
    private String status_description;
    private String reports_to_id;
    private String account_name;
    private String account_description;
    private String contact_id;
    private String account_id;
    private String opportunity_id;
    private String opportunity_name;
    private String opportunity_amount;
    private String campaign_id;
    private String birthdate;
    private String portal_name;
    private String portal_app;
    private String website;
    private String id_c;
    private String razonsocial_c;
    private String accion_c;
    private String fecha_c;
    private String fecha2_c;
    private String fecha3_c;
    private String retroalimentacion2_c;
    private String retroalimentacion3_c;
    private String responsable_c;
    private String responsable2_c;
    private String responsable3_c;
    private String fuente_c;
    private String medio_c;
    private String otro_c;
    private String tiposolicitud_c;
    private String valor_real_c;
    private String marca_c;
    private String estado_c;
    private String email_address;
    private String campaign_name;
    private String assigned_user_name;

    public ClienteDetalle(String id,
                          String date_entered,
                          String date_modified,
                          String modified_user_id,
                          String created_by,
                          String description,
                          String deleted,
                          String assigned_user_id,
                          String salutation,
                          String first_name,
                          String last_name,
                          String title,
                          String department,
                          String do_not_call,
                          String phone_home,
                          String phone_mobile,
                          String phone_work,
                          String phone_other,
                          String phone_fax,
                          String primary_address_street,
                          String primary_address_city,
                          String primary_address_state,
                          String primary_address_postalcode,
                          String primary_address_country,
                          String alt_address_street,
                          String alt_address_city,
                          String alt_address_state,
                          String alt_address_postalcode,
                          String alt_address_country,
                          String assistant,
                          String assistant_phone,
                          String converted,
                          String refered_by,
                          String lead_source,
                          String lead_source_description,
                          String status,
                          String status_description,
                          String reports_to_id,
                          String account_name,
                          String account_description,
                          String contact_id,
                          String account_id,
                          String opportunity_id,
                          String opportunity_name,
                          String opportunity_amount,
                          String campaign_id,
                          String birthdate,
                          String portal_name,
                          String portal_app,
                          String website,
                          String id_c,
                          String razonsocial_c,
                          String accion_c,
                          String fecha_c,
                          String fecha2_c,
                          String fecha3_c,
                          String retroalimentacion2_c,
                          String retroalimentacion3_c,
                          String responsable_c,
                          String responsable2_c,
                          String responsable3_c,
                          String fuente_c,
                          String medio_c,
                          String otro_c,
                          String tiposolicitud_c,
                          String valor_real_c,
                          String marca_c,
                          String estado_c,
                          String email_address,
                          String campaign_name,
                          String assigned_user_name) {
        setId(id);
        setDate_entered(date_entered);
        setDate_modified(date_modified);
        setModified_user_id(modified_user_id);
        setCreated_by(created_by);
        setDescription(description);
        setDeleted(deleted);
        setAssigned_user_id(assigned_user_id);
        setSalutation(salutation);
        setFirst_name(first_name);
        setLast_name(last_name);
        setTitle(title);
        setDepartment(department);
        setDo_not_call(do_not_call);
        setPhone_home(phone_home);
        setPhone_mobile(phone_mobile);
        setPhone_work(phone_work);
        setPhone_other(phone_other);
        setPhone_fax(phone_fax);
        setPrimary_address_street(primary_address_street);
        setPrimary_address_city(primary_address_city);
        setPrimary_address_state(primary_address_state);
        setPrimary_address_postalcode(primary_address_postalcode);
        setPrimary_address_country(primary_address_country);
        setAlt_address_street(alt_address_street);
        setAlt_address_city(alt_address_city);
        setAlt_address_state(alt_address_state);
        setAlt_address_postalcode(alt_address_postalcode);
        setAlt_address_country(alt_address_country);
        setAssistant(assistant);
        setAssistant_phone(assistant_phone);
        setConverted(converted);
        setRefered_by(refered_by);
        setLead_source(lead_source);
        setLead_source_description(lead_source_description);
        setStatus(status);
        setStatus_description(status_description);
        setReports_to_id(reports_to_id);
        setAccount_name(account_name);
        setAccount_description(account_description);
        setContact_id(contact_id);
        setAccount_id(account_id);
        setOpportunity_id(opportunity_id);
        setOpportunity_name(opportunity_name);
        setOpportunity_amount(opportunity_amount);
        setCampaign_id(campaign_id);
        setBirthdate(birthdate);
        setPortal_name(portal_name);
        setPortal_app(portal_app);
        setWebsite(website);
        setId_c(id_c);
        setRazonsocial_c(razonsocial_c);
        setAccion_c(accion_c);
        setFecha_c(fecha_c);
        setFecha2_c(fecha2_c);
        setFecha3_c(fecha3_c);
        setRetroalimentacion2_c(retroalimentacion2_c);
        setRetroalimentacion3_c(retroalimentacion3_c);
        setResponsable_c(responsable_c);
        setResponsable2_c(responsable2_c);
        setResponsable3_c(responsable3_c);
        setFuente_c(fuente_c);
        setMedio_c(medio_c);
        setOtro_c(otro_c);
        setTiposolicitud_c(tiposolicitud_c);
        setValor_real_c(valor_real_c);
        setMarca_c(marca_c);
        setEstado_c(estado_c);
        setEmail_address(email_address);
        setCampaign_name(campaign_name);
        setAssigned_user_name(assigned_user_name);
    }

    protected ClienteDetalle(Parcel in) {
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
        converted = validate(in.readString());
        refered_by = validate(in.readString());
        lead_source = validate(in.readString());
        lead_source_description = validate(in.readString());
        status = validate(in.readString());
        status_description = validate(in.readString());
        reports_to_id = validate(in.readString());
        account_name = validate(in.readString());
        account_description = validate(in.readString());
        contact_id = validate(in.readString());
        account_id = validate(in.readString());
        opportunity_id = validate(in.readString());
        opportunity_name = validate(in.readString());
        opportunity_amount = validate(in.readString());
        campaign_id = validate(in.readString());
        birthdate = validate(in.readString());
        portal_name = validate(in.readString());
        portal_app = validate(in.readString());
        website = validate(in.readString());
        id_c = validate(in.readString());
        razonsocial_c = validate(in.readString());
        accion_c = validate(in.readString());
        fecha_c = validate(in.readString());
        fecha2_c = validate(in.readString());
        fecha3_c = validate(in.readString());
        retroalimentacion2_c = validate(in.readString());
        retroalimentacion3_c = validate(in.readString());
        responsable_c = validate(in.readString());
        responsable2_c = validate(in.readString());
        responsable3_c = validate(in.readString());
        fuente_c = validate(in.readString());
        medio_c = validate(in.readString());
        otro_c = validate(in.readString());
        tiposolicitud_c = validate(in.readString());
        valor_real_c = validate(in.readString());
        marca_c = validate(in.readString());
        estado_c = validate(in.readString());
        email_address = validate(in.readString());
        campaign_name = validate(in.readString());
        assigned_user_name = validate(in.readString());
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

    public String getConverted() {
        return converted;
    }

    public void setConverted(String converted) {
        this.converted = converted;
    }

    public String getRefered_by() {
        return refered_by;
    }

    public void setRefered_by(String refered_by) {
        this.refered_by = refered_by;
    }

    public String getLead_source() {
        return lead_source;
    }

    public void setLead_source(String lead_source) {
        this.lead_source = lead_source;
    }

    public String getLead_source_description() {
        return lead_source_description;
    }

    public void setLead_source_description(String lead_source_description) {
        this.lead_source_description = lead_source_description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    public String getReports_to_id() {
        return reports_to_id;
    }

    public void setReports_to_id(String reports_to_id) {
        this.reports_to_id = reports_to_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_description() {
        return account_description;
    }

    public void setAccount_description(String account_description) {
        this.account_description = account_description;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getOpportunity_id() {
        return opportunity_id;
    }

    public void setOpportunity_id(String opportunity_id) {
        this.opportunity_id = opportunity_id;
    }

    public String getOpportunity_name() {
        return opportunity_name;
    }

    public void setOpportunity_name(String opportunity_name) {
        this.opportunity_name = opportunity_name;
    }

    public String getOpportunity_amount() {
        return opportunity_amount;
    }

    public void setOpportunity_amount(String opportunity_amount) {
        this.opportunity_amount = opportunity_amount;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPortal_name() {
        return portal_name;
    }

    public void setPortal_name(String portal_name) {
        this.portal_name = portal_name;
    }

    public String getPortal_app() {
        return portal_app;
    }

    public void setPortal_app(String portal_app) {
        this.portal_app = portal_app;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getRazonsocial_c() {
        return razonsocial_c;
    }

    public void setRazonsocial_c(String razonsocial_c) {
        this.razonsocial_c = razonsocial_c;
    }

    public String getAccion_c() {
        return accion_c;
    }

    public void setAccion_c(String accion_c) {
        this.accion_c = accion_c;
    }

    public String getFecha_c() {
        return fecha_c;
    }

    public void setFecha_c(String fecha_c) {
        this.fecha_c = fecha_c;
    }

    public String getFecha2_c() {
        return fecha2_c;
    }

    public void setFecha2_c(String fecha2_c) {
        this.fecha2_c = fecha2_c;
    }

    public String getFecha3_c() {
        return fecha3_c;
    }

    public void setFecha3_c(String fecha3_c) {
        this.fecha3_c = fecha3_c;
    }

    public String getRetroalimentacion2_c() {
        return retroalimentacion2_c;
    }

    public void setRetroalimentacion2_c(String retroalimentacion2_c) {
        this.retroalimentacion2_c = retroalimentacion2_c;
    }

    public String getRetroalimentacion3_c() {
        return retroalimentacion3_c;
    }

    public void setRetroalimentacion3_c(String retroalimentacion3_c) {
        this.retroalimentacion3_c = retroalimentacion3_c;
    }

    public String getResponsable_c() {
        return responsable_c;
    }

    public void setResponsable_c(String responsable_c) {
        this.responsable_c = responsable_c;
    }

    public String getResponsable2_c() {
        return responsable2_c;
    }

    public void setResponsable2_c(String responsable2_c) {
        this.responsable2_c = responsable2_c;
    }

    public String getResponsable3_c() {
        return responsable3_c;
    }

    public void setResponsable3_c(String responsable3_c) {
        this.responsable3_c = responsable3_c;
    }

    public String getFuente_c() {
        return fuente_c;
    }

    public void setFuente_c(String fuente_c) {
        this.fuente_c = fuente_c;
    }

    public String getMedio_c() {
        return medio_c;
    }

    public void setMedio_c(String medio_c) {
        this.medio_c = medio_c;
    }

    public String getOtro_c() {
        return otro_c;
    }

    public void setOtro_c(String otro_c) {
        this.otro_c = otro_c;
    }

    public String getTiposolicitud_c() {
        return tiposolicitud_c;
    }

    public void setTiposolicitud_c(String tiposolicitud_c) {
        this.tiposolicitud_c = tiposolicitud_c;
    }

    public String getValor_real_c() {
        return valor_real_c;
    }

    public void setValor_real_c(String valor_real_c) {
        this.valor_real_c = valor_real_c;
    }

    public String getMarca_c() {
        return marca_c;
    }

    public void setMarca_c(String marca_c) {
        this.marca_c = marca_c;
    }

    public String getEstado_c() {
        return estado_c;
    }

    public void setEstado_c(String estado_c) {
        this.estado_c = estado_c;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getAssigned_user_name() {
        return assigned_user_name;
    }

    public void setAssigned_user_name(String assigned_user_name) {
        this.assigned_user_name = assigned_user_name;
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
        dest.writeString(converted);
        dest.writeString(refered_by);
        dest.writeString(lead_source);
        dest.writeString(lead_source_description);
        dest.writeString(status);
        dest.writeString(status_description);
        dest.writeString(reports_to_id);
        dest.writeString(account_name);
        dest.writeString(account_description);
        dest.writeString(contact_id);
        dest.writeString(account_id);
        dest.writeString(opportunity_id);
        dest.writeString(opportunity_name);
        dest.writeString(opportunity_amount);
        dest.writeString(campaign_id);
        dest.writeString(birthdate);
        dest.writeString(portal_name);
        dest.writeString(portal_app);
        dest.writeString(website);
        dest.writeString(id_c);
        dest.writeString(razonsocial_c);
        dest.writeString(accion_c);
        dest.writeString(fecha_c);
        dest.writeString(fecha2_c);
        dest.writeString(fecha3_c);
        dest.writeString(retroalimentacion2_c);
        dest.writeString(retroalimentacion3_c);
        dest.writeString(responsable_c);
        dest.writeString(responsable2_c);
        dest.writeString(responsable3_c);
        dest.writeString(fuente_c);
        dest.writeString(medio_c);
        dest.writeString(otro_c);
        dest.writeString(tiposolicitud_c);
        dest.writeString(valor_real_c);
        dest.writeString(marca_c);
        dest.writeString(estado_c);
        dest.writeString(email_address);
        dest.writeString(campaign_name);
        dest.writeString(assigned_user_name);
    }

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return first_name + " " + last_name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
}
