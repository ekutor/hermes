package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las oportunidades.
 */
public class OportunidadDetalle implements Parcelable {


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OportunidadDetalle> CREATOR = new Parcelable.Creator<OportunidadDetalle>() {
        @Override
        public OportunidadDetalle createFromParcel(Parcel in) {
            return new OportunidadDetalle(in);
        }

        @Override
        public OportunidadDetalle[] newArray(int size) {
            return new OportunidadDetalle[size];
        }
    };
    private String id;
    private String name;
    private String date_entered;
    private String date_modified;
    private String modified_user_id;
    private String created_by;
    private String description;
    private String deleted;
    private String assigned_user_id;
    private String opportunity_type;
    private String campaign_id;
    private String lead_source;
    private String amount;
    private String amount_usdollar;
    private String currency_id;
    private String date_closed;
    private String next_step;
    private String sales_stage;
    private String probability;
    private String id_c;
    private String valoroportunidad_c;
    private String medio_c;
    private String fuente_c;
    private String tipo_c;
    private String energia_c;
    private String comunicaciones_c;
    private String iluminacion_c;
    private String usuario_final_c;
    private String idAccount;
    private String nameAccount;
    private String nameCampaign;
    private String assigned_user_name;


    public OportunidadDetalle(String id,
                              String name,
                              String date_entered,
                              String date_modified,
                              String modified_user_id,
                              String created_by,
                              String description,
                              String deleted,
                              String assigned_user_id,
                              String opportunity_type,
                              String campaign_id,
                              String lead_source,
                              String amount,
                              String amount_usdollar,
                              String currency_id,
                              String date_closed,
                              String next_step,
                              String sales_stage,
                              String probability,
                              String id_c,
                              String valoroportunidad_c,
                              String medio_c,
                              String fuente_c,
                              String tipo_c,
                              String energia_c,
                              String comunicaciones_c,
                              String iluminacion_c,
                              String usuario_final_c,
                              String idAccount,
                              String nameAccount,
                              String nameCampaign,
                              String assigned_user_name) {
        setId(id);
        setName(name);
        setDate_entered(date_entered);
        setDate_modified(date_modified);
        setModified_user_id(modified_user_id);
        setCreated_by(created_by);
        setDescription(description);
        setDeleted(deleted);
        setAssigned_user_id(assigned_user_id);
        setOpportunity_type(opportunity_type);
        setCampaign_id(campaign_id);
        setLead_source(lead_source);
        setAmount(amount);
        setAmount_usdollar(amount_usdollar);
        setCurrency_id(currency_id);
        setDate_closed(date_closed);
        setNext_step(next_step);
        setSales_stage(sales_stage);
        setProbability(probability);
        setId(id_c);
        setValoroportunidad_c(valoroportunidad_c);
        setMedio_c(medio_c);
        setFuente_c(fuente_c);
        setTipo_c(tipo_c);
        setEnergia_c(energia_c);
        setComunicaciones_c(comunicaciones_c);
        setIluminacion_c(iluminacion_c);
        setUsuario_final_c(usuario_final_c);
        setIdAccount(idAccount);
        setNameAccount(nameAccount);
        setNameCampaign(nameCampaign);
        setAssigned_user_name(assigned_user_name);
    }

    protected OportunidadDetalle(Parcel in) {
        id = in.readString();
        name = in.readString();
        date_entered = in.readString();
        date_modified = in.readString();
        modified_user_id = in.readString();
        created_by = in.readString();
        description = in.readString();
        deleted = in.readString();
        assigned_user_id = in.readString();
        opportunity_type = in.readString();
        campaign_id = in.readString();
        lead_source = in.readString();
        amount = in.readString();
        amount_usdollar = in.readString();
        currency_id = in.readString();
        date_closed = in.readString();
        next_step = in.readString();
        sales_stage = in.readString();
        probability = in.readString();
        id_c = in.readString();
        valoroportunidad_c = in.readString();
        medio_c = in.readString();
        fuente_c = in.readString();
        tipo_c = in.readString();
        energia_c = in.readString();
        comunicaciones_c = in.readString();
        iluminacion_c = in.readString();
        usuario_final_c = in.readString();
        idAccount = in.readString();
        nameAccount = in.readString();
        nameCampaign = in.readString();
        assigned_user_name = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOpportunity_type() {
        return opportunity_type;
    }

    public void setOpportunity_type(String opportunity_type) {
        this.opportunity_type = opportunity_type;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getLead_source() {
        return lead_source;
    }

    public void setLead_source(String lead_source) {
        this.lead_source = lead_source;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount_usdollar() {
        return amount_usdollar;
    }

    public void setAmount_usdollar(String amount_usdollar) {
        this.amount_usdollar = amount_usdollar;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getDate_closed() {
        return date_closed;
    }

    public void setDate_closed(String date_closed) {
        this.date_closed = date_closed;
    }

    public String getNext_step() {
        return next_step;
    }

    public void setNext_step(String next_step) {
        this.next_step = next_step;
    }

    public String getSales_stage() {
        return sales_stage;
    }

    public void setSales_stage(String sales_stage) {
        this.sales_stage = sales_stage;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getValoroportunidad_c() {
        return valoroportunidad_c;
    }

    public void setValoroportunidad_c(String valoroportunidad_c) {
        this.valoroportunidad_c = valoroportunidad_c;
    }

    public String getMedio_c() {
        return medio_c;
    }

    public void setMedio_c(String medio_c) {
        this.medio_c = medio_c;
    }

    public String getFuente_c() {
        return fuente_c;
    }

    public void setFuente_c(String fuente_c) {
        this.fuente_c = fuente_c;
    }

    public String getTipo_c() {
        return tipo_c;
    }

    public void setTipo_c(String tipo_c) {
        this.tipo_c = tipo_c;
    }

    public String getEnergia_c() {
        return energia_c;
    }

    public void setEnergia_c(String energia_c) {
        this.energia_c = energia_c;
    }

    public String getComunicaciones_c() {
        return comunicaciones_c;
    }

    public void setComunicaciones_c(String comunicaciones_c) {
        this.comunicaciones_c = comunicaciones_c;
    }

    public String getIluminacion_c() {
        return iluminacion_c;
    }

    public void setIluminacion_c(String iluminacion_c) {
        this.iluminacion_c = iluminacion_c;
    }

    public String getUsuario_final_c() {
        return usuario_final_c;
    }

    public void setUsuario_final_c(String usuario_final_c) {
        this.usuario_final_c = usuario_final_c;
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

    public String getNameCampaign() {
        return nameCampaign;
    }

    public void setNameCampaign(String nameCampaign) {
        this.nameCampaign = nameCampaign;
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
        dest.writeString(name);
        dest.writeString(date_entered);
        dest.writeString(date_modified);
        dest.writeString(modified_user_id);
        dest.writeString(created_by);
        dest.writeString(description);
        dest.writeString(deleted);
        dest.writeString(assigned_user_id);
        dest.writeString(opportunity_type);
        dest.writeString(campaign_id);
        dest.writeString(lead_source);
        dest.writeString(amount);
        dest.writeString(amount_usdollar);
        dest.writeString(currency_id);
        dest.writeString(date_closed);
        dest.writeString(next_step);
        dest.writeString(sales_stage);
        dest.writeString(probability);
        dest.writeString(id_c);
        dest.writeString(valoroportunidad_c);
        dest.writeString(medio_c);
        dest.writeString(fuente_c);
        dest.writeString(tipo_c);
        dest.writeString(energia_c);
        dest.writeString(comunicaciones_c);
        dest.writeString(iluminacion_c);
        dest.writeString(usuario_final_c);
        dest.writeString(idAccount);
        dest.writeString(nameAccount);
        dest.writeString(nameCampaign);
        dest.writeString(assigned_user_name);
    }
}
