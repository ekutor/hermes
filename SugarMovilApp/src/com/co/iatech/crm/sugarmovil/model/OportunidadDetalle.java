package com.co.iatech.crm.sugarmovil.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.util.Utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las oportunidades.
 */
public class OportunidadDetalle extends GenericBean implements Parcelable {


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

    
    public OportunidadDetalle(){
    	
    }
    public OportunidadDetalle(JSONObject obj) throws JSONException {
    	setId(validate(obj.getString("id")));
    	setName(validate(obj.getString("name")));
    	setDate_entered(validate(obj.getString("date_entered")));
    	setDate_modified(validate(obj.getString("date_modified")));
    	setModified_user_id(validate(obj.getString("modified_user_id")));
    	setCreated_by(validate(obj.getString("created_by")));
    	setDescription(validate(obj.getString("description")));
    	setDeleted(validate(obj.getString("deleted")));
    	setAssigned_user_id(validate(obj.getString("assigned_user_id")));
    	setOpportunity_type(validate(obj.getString("opportunity_type")));
    	setCampaign_id(validate(obj.getString("campaign_id")));
    	setLead_source(validate(obj.getString("lead_source")));
    	setAmount(validate(obj.getString("amount")));
    	setAmount_usdollar(validate(obj.getString("amount_usdollar")));
    	setCurrency_id(validate(obj.getString("currency_id")));
    	setDate_closed(validate(obj.getString("date_closed")));
    	setNext_step(validate(obj.getString("next_step")));
    	setSales_stage(validate(obj.getString("sales_stage")));
    	setProbability(validate(obj.getString("probability")));
    	setId_c(validate(obj.getString("id_c")));
    	setValoroportunidad_c(validate(obj.getString("valoroportunidad_c")));
    	setMedio_c(validate(obj.getString("medio_c")));
    	setFuente_c(validate(obj.getString("fuente_c")));
    	setTipo_c(validate(obj.getString("tipo_c")));
    	setEnergia_c(validate(obj.getString("energia_c")));
    	setComunicaciones_c(validate(obj.getString("comunicaciones_c")));
    	setIluminacion_c(validate(obj.getString("iluminacion_c")));
    	setUsuario_final_c(validate(obj.getString("usuario_final_c")));
    	setIdAccount(validate(obj.getString("idAccount")));
    	setNameAccount(validate(obj.getString("nameAccount")));
    	setNameCampaign(validate(obj.getString("nameCampaign")));
    	setAssigned_user_name(validate(obj.getString("assigned_user_name")));
    }

    protected OportunidadDetalle(Parcel in) {
    	id = validate(in.readString());
    	name = validate(in.readString());
    	date_entered = validate(in.readString());
    	date_modified = validate(in.readString());
    	modified_user_id = validate(in.readString());
    	created_by = validate(in.readString());
    	description = validate(in.readString());
    	deleted = validate(in.readString());
    	assigned_user_id = validate(in.readString());
    	opportunity_type = validate(in.readString());
    	campaign_id = validate(in.readString());
    	lead_source = validate(in.readString());
    	amount = validate(in.readString());
    	amount_usdollar = validate(in.readString());
    	currency_id = validate(in.readString());
    	date_closed = validate(in.readString());
    	next_step = validate(in.readString());
    	sales_stage = validate(in.readString());
    	probability = validate(in.readString());
    	id_c = validate(in.readString());
    	valoroportunidad_c = validate(in.readString());
    	medio_c = validate(in.readString());
    	fuente_c = validate(in.readString());
    	tipo_c = validate(in.readString());
    	energia_c = validate(in.readString());
    	comunicaciones_c = validate(in.readString());
    	iluminacion_c = validate(in.readString());
    	usuario_final_c = validate(in.readString());
    	idAccount = validate(in.readString());
    	nameAccount = validate(in.readString());
    	nameCampaign = validate(in.readString());
    	assigned_user_name = validate(in.readString());
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
        this.valoroportunidad_c = Utils.delSpecialChars(valoroportunidad_c);
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
    	return deleteSpecialChar(energia_c);
    }

    public void setEnergia_c(String energia_c) {
        this.energia_c = energia_c;
    }

    public String getComunicaciones_c() {
        return deleteSpecialChar(comunicaciones_c);
    }

    public void setComunicaciones_c(String comunicaciones_c) {
        this.comunicaciones_c = comunicaciones_c;
    }

    public String getIluminacion_c() {
        return deleteSpecialChar(iluminacion_c);
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
	@Override
	public Map<String, String> getDataBean() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id",id);
		data.put("name",name);
		data.put("date_entered",date_entered);
		data.put("date_modified",date_modified);
		data.put("modified_user_id",modified_user_id);
		data.put("created_by",created_by);
		data.put("description",Utils.hideTabs(description));
		data.put("deleted",deleted);
		data.put("assigned_user_id",assigned_user_id);
		data.put("opportunity_type",opportunity_type);
		data.put("campaign_id",campaign_id);
		data.put("lead_source",lead_source);
		data.put("amount",amount);
		data.put("amount_usdollar",amount_usdollar);
		data.put("currency_id",currency_id);
		data.put("date_closed",date_closed);
		data.put("next_step",next_step);
		data.put("sales_stage",sales_stage);
		data.put("probability",probability);
		data.put("id_c",id_c);
		data.put("valoroportunidad_c",valoroportunidad_c);
		data.put("medio_c",medio_c);
		data.put("fuente_c",fuente_c);
		data.put("tipo_c",tipo_c);
		data.put("energia_c",addSpecialChar(energia_c));
		data.put("comunicaciones_c",addSpecialChar(comunicaciones_c));
		data.put("iluminacion_c",addSpecialChar(iluminacion_c));
		data.put("usuario_final_c",usuario_final_c);
		data.put("idAccount",idAccount);
		data.put("nameAccount",nameAccount);
		data.put("nameCampaign",nameCampaign);
		data.put("assigned_user_name",assigned_user_name);
		return data;
	}
}
