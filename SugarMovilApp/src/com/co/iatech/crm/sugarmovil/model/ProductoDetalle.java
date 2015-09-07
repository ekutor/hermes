package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de un producto.
 */
public class ProductoDetalle extends GenericBean implements Parcelable {


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProductoDetalle> CREATOR = new Parcelable.Creator<ProductoDetalle>() {
        @Override
        public ProductoDetalle createFromParcel(Parcel in) {
            return new ProductoDetalle(in);
        }

        @Override
        public ProductoDetalle[] newArray(int size) {
            return new ProductoDetalle[size];
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
    private String id_c;
    private String codigo_c;
    private String referencia_c;
    private String marca_c;
    private String grupo_c;
    private String en_inventario_c;
    private String precio1_c;
    private String precio2_c;

    public ProductoDetalle(JSONObject obj) throws JSONException {
    	setId(validate(obj.getString("id")));;
        setName(validate(obj.getString("name")));;
        setDate_entered(validate(obj.getString("date_entered")));;
        setDate_modified(validate(obj.getString("date_modified")));;
        setModified_user_id(validate(obj.getString("modified_user_id")));;
        setCreated_by(validate(obj.getString("created_by")));;
        setDescription(validate(obj.getString("description")));;
        setDeleted(validate(obj.getString("deleted")));;
        setAssigned_user_id(validate(obj.getString("assigned_user_id")));;
        setId_c(validate(obj.getString("id_c")));;
        setCodigo_c(validate(obj.getString("codigo_c")));;
        setReferencia_c(validate(obj.getString("referencia_c")));;
        setMarca_c(validate(obj.getString("marca_c")));;
        setGrupo_c(validate(obj.getString("grupo_c")));;
        setEn_inventario_c(validate(obj.getString("en_inventario_c")));;
        setPrecio1_c(validate(obj.getString("precio1_c")));;
        setPrecio2_c(validate(obj.getString("precio2_c")));;
    }

    protected ProductoDetalle(Parcel in) {
        id = in.readString();
        name = in.readString();
        date_entered = in.readString();
        date_modified = in.readString();
        modified_user_id = in.readString();
        created_by = in.readString();
        description = in.readString();
        deleted = in.readString();
        assigned_user_id = in.readString();
        id_c = in.readString();
        codigo_c = in.readString();
        referencia_c = in.readString();
        marca_c = in.readString();
        grupo_c = in.readString();
        en_inventario_c = in.readString();
        precio1_c = in.readString();
        precio2_c = in.readString();
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

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getCodigo_c() {
        return codigo_c;
    }

    public void setCodigo_c(String codigo_c) {
        this.codigo_c = codigo_c;
    }

    public String getReferencia_c() {
        return referencia_c;
    }

    public void setReferencia_c(String referencia_c) {
        this.referencia_c = referencia_c;
    }

    public String getMarca_c() {
        return marca_c;
    }

    public void setMarca_c(String marca_c) {
        this.marca_c = marca_c;
    }

    public String getGrupo_c() {
        return grupo_c;
    }

    public void setGrupo_c(String grupo_c) {
        this.grupo_c = grupo_c;
    }

    public String getEn_inventario_c() {
        return en_inventario_c;
    }

    public void setEn_inventario_c(String en_inventario_c) {
        this.en_inventario_c = en_inventario_c;
    }

    public String getPrecio1_c() {
        return precio1_c;
    }

    public void setPrecio1_c(String precio1_c) {
        this.precio1_c = precio1_c;
    }

    public String getPrecio2_c() {
        return precio2_c;
    }

    public void setPrecio2_c(String precio2_c) {
        this.precio2_c = precio2_c;
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
        dest.writeString(id_c);
        dest.writeString(codigo_c);
        dest.writeString(referencia_c);
        dest.writeString(marca_c);
        dest.writeString(grupo_c);
        dest.writeString(en_inventario_c);
        dest.writeString(precio1_c);
        dest.writeString(precio2_c);
    }

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
}