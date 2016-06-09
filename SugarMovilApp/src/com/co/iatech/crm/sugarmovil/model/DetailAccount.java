package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de una cuenta.
 */
public class DetailAccount extends GenericBean implements Parcelable {

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<DetailAccount> CREATOR = new Parcelable.Creator<DetailAccount>() {
		@Override
		public DetailAccount createFromParcel(Parcel in) {
			return new DetailAccount(in);
		}

		@Override
		public DetailAccount[] newArray(int size) {
			return new DetailAccount[size];
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
	private String account_type;
	private String industry;
	private String annual_revenue;
	private String phone_fax;
	private String billing_address_street;
	private String billing_address_city;
	private String billing_address_state;
	private String billing_address_postalcode;
	private String billing_address_country;
	private String rating;
	private String phone_office;
	private String phone_alternate;
	private String website;
	private String ownership;
	private String employees;
	private String ticker_symbol;
	private String shipping_address_street;
	private String shipping_address_city;
	private String shipping_address_state;
	private String shipping_address_postalcode;
	private String shipping_address_country;
	private String parent_id;
	private String sic_code;
	private String campaign_id;
	private String id_c;
	private String canal_c;
	private String sector_c;
	private String extension1_c;
	private String extension2_c;
	private String celular_c;
	private String direccion_c;
	private String departamento_c;
	private String municipio_c;
	private String zona_c;
	private String uen_c;
	private String descuentocomercial_c;
	private String presupuestoanual_c;
	private String condpago_c;
	private String plpago_c;
	private String prompago_c;
	private String carteravencida_c;
	private String carteravencer_c;
	private String grupo_objetivo_c;
	private String segmento_c;
	private String estado_c;
	private String origencuenta_c;
	// private String alianzasestrategicas_c;
	private String automatizacionmoeller_c;
	private String enerlux_c;
	private String delta_c;
	private String dkc_c;
	private String leviton_c;
	private String wvcai_c;
	private String wohner_c;
	private String comunicaciones_c;
	private String preciosmineaton_c;
	private String maniobraeaton_c;
	private String descuento1_c;
	private String descuento2_c;
	private String descuento3_c;
	private String descuento4_c;
	private String descuento5_c;
	private String descuento6_c;
	private String descuento7_c;
	private String descuento8_c;
	private String descuento9_c;
	private String descuento10_c;
	private String bonosespeciales_c;
	private String bonificacioncompra_c;
	private String maniobraeatonanual_c;
	private String bonificacionleviton_c;
	private String meta1_c;
	private String meta2_c;
	private String meta3_c;
	private String meta4_c;
	private String bonificacion1_c;
	private String bonificacion2_c;
	private String bonificacion3_c;
	private String bonificacion4_c;
	private String despachoremsion_c;
	private String nocobro_c;
	private String estrategia1_c;
	private String estrategia2_c;
	private String estrategia3_c;
	private String estrategia4_c;
	private String fechaempresa_c;
	private String diamarca_c;
	private String exhibidor_c;
	private String apoya_c;
	private String placaaniversario_c;
	private String certificaciones_c;
	private String fechamarca_c;
	private String entregaexhibidor1_c;
	private String entregaexhibidor2_c;
	private String entregaexhibidor3_c;
	private String fechaapoya_c;
	private String fechaplaca_c;
	private String otrasestrategias_c;
	private String imagen1_c;
	private String imagen2_c;
	private String imagen3_c;
	private String acta1_c;
	private String acta2_c;
	private String acta3_c;
	private String pieza_c;
	private String fechaapoya2_c;
	private String pieza2_c;
	private String fechafacturacion_c;
	private String correotransporte_c;
	private String laumayer2_c;
	private String ventasactual_c;
	private String ventasanterior_c;
	// private String numeroalianzas_c;
	private String cupocr_c;
	private String facturacionmes_c;
	private String cupodisponible_c;
	private String nit_c;
	private String facturaciondiara_c;
	private String facturacionautorizada_c;
	private String cod_alterno_c;
	private String totalcartera_c;
	private String remesa_c;
	private String destino_c;
	private String nombredestinatario_c;
	private String unidades_c;
	private String documento_c;
	private String nombredestinatario2_c;
	private String destino2_c;
	private String motivo_c;
	private String facturacionnoautorizada_c;
	private String porcentaje_cumplimiento_c;
	private String fecha_despacho_c;
	private String prue_c;
	private String prueba_c;
	private String preuba_c;
	private String assigned_user_name;
	private String email_address;
	private String descuentocomercial2_c;
	private String dkc2_c;
	private String descuento11_c;
	private String oemxenergy_c;
	private String activos_actual_c;
	private String activos_anterior_c;
	private String clase_c;
	private String pasivos_actual_c;
	private String pasivos_anterior_c;

	public DetailAccount() {

	}

	public DetailAccount(JSONObject obj) throws JSONException {

		setId(validate(obj.getString("id")));
		setName(validate(obj.getString("name")));
		setDate_entered(validate(obj.getString("date_entered")));
		setDate_modified(validate(obj.getString("date_modified")));
		setModified_user_id(validate(obj.getString("modified_user_id")));
		setCreated_by(validate(obj.getString("created_by")));
		setDescription(validate(obj.getString("description")));
		setDeleted(validate(obj.getString("deleted")));
		setAssigned_user_id(validate(obj.getString("assigned_user_id")));
		setAccount_type(validate(obj.getString("account_type")));
		setIndustry(validate(obj.getString("industry")));
		setAnnual_revenue(validate(obj.getString("annual_revenue")));
		setPhone_fax(validate(obj.getString("phone_fax")));
		setBilling_address_street(validate(obj.getString("billing_address_street")));
		setBilling_address_city(validate(obj.getString("billing_address_city")));
		setBilling_address_state(validate(obj.getString("billing_address_state")));
		setBilling_address_postalcode(validate(obj.getString("billing_address_postalcode")));
		setBilling_address_country(validate(obj.getString("billing_address_country")));
		setRating(validate(obj.getString("rating")));
		setPhone_office(validate(obj.getString("phone_office")));
		setPhone_alternate(validate(obj.getString("phone_alternate")));
		setWebsite(validate(obj.getString("website")));
		setOwnership(validate(obj.getString("ownership")));
		setEmployees(validate(obj.getString("employees")));
		setTicker_symbol(validate(obj.getString("ticker_symbol")));
		setShipping_address_street(validate(obj.getString("shipping_address_street")));
		setShipping_address_city(validate(obj.getString("shipping_address_city")));
		setShipping_address_state(validate(obj.getString("shipping_address_state")));
		setShipping_address_postalcode(validate(obj.getString("shipping_address_postalcode")));
		setShipping_address_country(validate(obj.getString("shipping_address_country")));
		setParent_id(validate(obj.getString("parent_id")));
		setSic_code(validate(obj.getString("sic_code")));
		setCampaign_id(validate(obj.getString("campaign_id")));
		setId_c(validate(obj.getString("id_c")));
		setCanal_c(validate(obj.getString("canal_c")));
		setSector_c(validate(obj.getString("sector_c")));
		setExtension1_c(validate(obj.getString("extension1_c")));
		setExtension2_c(validate(obj.getString("extension2_c")));
		setCelular_c(validate(obj.getString("celular_c")));
		setDireccion_c(validate(obj.getString("direccion_c")));
		setDepartamento_c(validate(obj.getString("departamento_c")));
		setMunicipio_c(validate(obj.getString("municipio_c")));
		setZona_c(validate(obj.getString("zona_c")));
		setUen_c(validate(obj.getString("uen_c")));
		setDescuentocomercial_c(validate(obj.getString("descuentocomercial_c")));
		setPresupuestoanual_c(validate(obj.getString("presupuestoanual_c")));
		setCondpago_c(validate(obj.getString("condpago_c")));
		setPlpago_c(validate(obj.getString("plpago_c")));
		setPrompago_c(validate(obj.getString("prompago_c")));
		setCarteravencida_c(validate(obj.getString("carteravencida_c")));
		setCarteravencer_c(validate(obj.getString("carteravencer_c")));
		setGrupo_objetivo_c(validate(obj.getString("grupo_objetivo_c")));
		setSegmento_c(validate(obj.getString("segmento_c")));
		setEstado_c(validate(obj.getString("estado_c")));
		setOrigencuenta_c(validate(obj.getString("origencuenta_c")));
		// setAlianzasestrategicas_c(validate(obj.getString("alianzasestrategicas_c")));
		setAutomatizacionmoeller_c(validate(obj.getString("automatizacionmoeller_c")));
		setEnerlux_c(validate(obj.getString("enerlux_c")));
		setDelta_c(validate(obj.getString("delta_c")));
		setDkc_c(validate(obj.getString("dkc_c")));
		setLeviton_c(validate(obj.getString("leviton_c")));
		setWvcai_c(validate(obj.getString("wvcai_c")));
		setWohner_c(validate(obj.getString("wohner_c")));
		setComunicaciones_c(validate(obj.getString("comunicaciones_c")));
		setPreciosmineaton_c(validate(obj.getString("preciosmineaton_c")));
		setManiobraeaton_c(validate(obj.getString("maniobraeaton_c")));
		setDescuento1_c(validate(obj.getString("descuento1_c")));
		setDescuento2_c(validate(obj.getString("descuento2_c")));
		setDescuento3_c(validate(obj.getString("descuento3_c")));
		setDescuento4_c(validate(obj.getString("descuento4_c")));
		setDescuento5_c(validate(obj.getString("descuento5_c")));
		setDescuento6_c(validate(obj.getString("descuento6_c")));
		setDescuento7_c(validate(obj.getString("descuento7_c")));
		setDescuento8_c(validate(obj.getString("descuento8_c")));
		setDescuento9_c(validate(obj.getString("descuento9_c")));
		setDescuento10_c(validate(obj.getString("descuento10_c")));
		setBonosespeciales_c(validate(obj.getString("bonosespeciales_c")));
		setBonificacioncompra_c(validate(obj.getString("bonificacioncompra_c")));
		setManiobraeatonanual_c(validate(obj.getString("maniobraeatonanual_c")));
		setBonificacionleviton_c(validate(obj.getString("bonificacionleviton_c")));
		setMeta1_c(validate(obj.getString("meta1_c")));
		setMeta2_c(validate(obj.getString("meta2_c")));
		setMeta3_c(validate(obj.getString("meta3_c")));
		setMeta4_c(validate(obj.getString("meta4_c")));
		setBonificacion1_c(validate(obj.getString("bonificacion1_c")));
		setBonificacion2_c(validate(obj.getString("bonificacion2_c")));
		setBonificacion3_c(validate(obj.getString("bonificacion3_c")));
		setBonificacion4_c(validate(obj.getString("bonificacion4_c")));
		setDespachoremsion_c(validate(obj.getString("despachoremsion_c")));
		setNocobro_c(validate(obj.getString("nocobro_c")));
		setEstrategia1_c(validate(obj.getString("estrategia1_c")));
		setEstrategia2_c(validate(obj.getString("estrategia2_c")));
		setEstrategia3_c(validate(obj.getString("estrategia3_c")));
		setEstrategia4_c(validate(obj.getString("estrategia4_c")));
		setFechaempresa_c(validate(obj.getString("fechaempresa_c")));
		setDiamarca_c(validate(obj.getString("diamarca_c")));
		setExhibidor_c(validate(obj.getString("exhibidor_c")));
		setApoya_c(validate(obj.getString("apoya_c")));
		setPlacaaniversario_c(validate(obj.getString("placaaniversario_c")));
		setCertificaciones_c(validate(obj.getString("certificaciones_c")));
		setFechamarca_c(validate(obj.getString("fechamarca_c")));
		setEntregaexhibidor1_c(validate(obj.getString("entregaexhibidor1_c")));
		setEntregaexhibidor2_c(validate(obj.getString("entregaexhibidor2_c")));
		setEntregaexhibidor3_c(validate(obj.getString("entregaexhibidor3_c")));
		setFechaapoya_c(validate(obj.getString("fechaapoya_c")));
		setFechaplaca_c(validate(obj.getString("fechaplaca_c")));
		setOtrasestrategias_c(validate(obj.getString("otrasestrategias_c")));
		setImagen1_c(validate(obj.getString("imagen1_c")));
		setImagen2_c(validate(obj.getString("imagen2_c")));
		setImagen3_c(validate(obj.getString("imagen3_c")));
		setActa1_c(validate(obj.getString("acta1_c")));
		setActa2_c(validate(obj.getString("acta2_c")));
		setActa3_c(validate(obj.getString("acta3_c")));
		setPieza_c(validate(obj.getString("pieza_c")));
		setFechaapoya2_c(validate(obj.getString("fechaapoya2_c")));
		setPieza2_c(validate(obj.getString("pieza2_c")));
		setFechafacturacion_c(validate(obj.getString("fechafacturacion_c")));
		setCorreotransporte_c(validate(obj.getString("correotransporte_c")));
		setLaumayer2_c(validate(obj.getString("laumayer2_c")));
		setVentasactual_c(validate(obj.getString("ventasactual_c")));
		setVentasanterior_c(validate(obj.getString("ventasanterior_c")));
		// setNumeroalianzas_c(validate(obj.getString("numeroalianzas_c")));
		setCupocr_c(validate(obj.getString("cupocr_c")));
		setFacturacionmes_c(validate(obj.getString("facturacionmes_c")));
		setCupodisponible_c(validate(obj.getString("cupodisponible_c")));
		setNit_c(validate(obj.getString("nit_c")));
		setFacturaciondiara_c(validate(obj.getString("facturaciondiara_c")));
		setFacturacionautorizada_c(validate(obj.getString("facturacionautorizada_c")));
		setCod_alterno_c(validate(obj.getString("cod_alterno_c")));
		setTotalcartera_c(validate(obj.getString("totalcartera_c")));
		setRemesa_c(validate(obj.getString("remesa_c")));
		setDestino_c(validate(obj.getString("destino_c")));
		setNombredestinatario_c(validate(obj.getString("nombredestinatario_c")));
		setUnidades_c(validate(obj.getString("unidades_c")));
		setDocumento_c(validate(obj.getString("documento_c")));
		setNombredestinatario2_c(validate(obj.getString("nombredestinatario2_c")));
		setDestino2_c(validate(obj.getString("destino2_c")));
		setMotivo_c(validate(obj.getString("motivo_c")));
		setFacturacionautorizada_c(validate(obj.getString("facturacionnoautorizada_c")));
		setPorcentaje_cumplimiento_c(validate(obj.getString("porcentaje_cumplimiento_c")));
		setFecha_despacho_c(validate(obj.getString("fecha_despacho_c")));
		setPrue_c(validate(obj.getString("prue_c")));
		setPrueba_c(validate(obj.getString("prueba_c")));
		setPrueba_c(validate(obj.getString("preuba_c")));
		setAssigned_user_name(validate(obj.getString("assigned_user_name")));
		setEmail_address(validate(obj.getString("email_address")));
		setDescuentocomercial2_c(validate(obj.getString("descuentocomercial2_c")));
		setDkc2_c(validate(obj.getString("dkc2_c")));
		setDescuento11_c(validate(obj.getString("descuento11_c")));
		setOemxenergy_c(validate(obj.getString("oemxenergy_c")));
		setActivos_actual_c(validate(obj.getString("activos_actual_c")));
		setActivos_anterior_c(validate(obj.getString("activos_anterior_c")));
		setClase_c(validate(obj.getString("clase_c")));
		setPasivos_actual_c(validate(obj.getString("pasivos_actual_c")));
		setPasivos_anterior_c(validate(obj.getString("pasivos_anterior_c")));
	}

	protected DetailAccount(Parcel in) {
		setId(validate(in.readString()));
		setName(validate(in.readString()));
		setDate_entered(validate(in.readString()));
		setDate_modified(validate(in.readString()));
		setModified_user_id(validate(in.readString()));
		setCreated_by(validate(in.readString()));
		setDescription(validate(in.readString()));
		setDeleted(validate(in.readString()));
		setAssigned_user_id(validate(in.readString()));
		setAccount_type(validate(in.readString()));
		setIndustry(validate(in.readString()));
		setAnnual_revenue(validate(in.readString()));
		setPhone_fax(validate(in.readString()));
		setBilling_address_street(validate(in.readString()));
		setBilling_address_city(validate(in.readString()));
		setBilling_address_state(validate(in.readString()));
		setBilling_address_postalcode(validate(in.readString()));
		setBilling_address_country(validate(in.readString()));
		setRating(validate(in.readString()));
		setPhone_office(validate(in.readString()));
		setPhone_alternate(validate(in.readString()));
		setWebsite(validate(in.readString()));
		setOwnership(validate(in.readString()));
		setEmployees(validate(in.readString()));
		setTicker_symbol(validate(in.readString()));
		setShipping_address_street(validate(in.readString()));
		setShipping_address_city(validate(in.readString()));
		setShipping_address_state(validate(in.readString()));
		setShipping_address_postalcode(validate(in.readString()));
		setShipping_address_country(validate(in.readString()));
		setParent_id(validate(in.readString()));
		setSic_code(validate(in.readString()));
		setCampaign_id(validate(in.readString()));
		setId_c(validate(in.readString()));
		setCanal_c(validate(in.readString()));
		setSector_c(validate(in.readString()));
		setExtension1_c(validate(in.readString()));
		setExtension2_c(validate(in.readString()));
		setCelular_c(validate(in.readString()));
		setDireccion_c(validate(in.readString()));
		setDepartamento_c(validate(in.readString()));
		setMunicipio_c(validate(in.readString()));
		setZona_c(validate(in.readString()));
		setUen_c(validate(in.readString()));
		setDescuentocomercial_c(validate(in.readString()));
		setPresupuestoanual_c(validate(in.readString()));
		setCondpago_c(validate(in.readString()));
		setPlpago_c(validate(in.readString()));
		setPrompago_c(validate(in.readString()));
		setCarteravencida_c(validate(in.readString()));
		setCarteravencer_c(validate(in.readString()));
		setGrupo_objetivo_c(validate(in.readString()));
		setSegmento_c(validate(in.readString()));
		setEstado_c(validate(in.readString()));
		setOrigencuenta_c(validate(in.readString()));
		// setAlianzasestrategicas_c(validate(in.readString()));
		setAutomatizacionmoeller_c(validate(in.readString()));
		setEnerlux_c(validate(in.readString()));
		setDelta_c(validate(in.readString()));
		setDkc_c(validate(in.readString()));
		setLeviton_c(validate(in.readString()));
		setWvcai_c(validate(in.readString()));
		setWohner_c(validate(in.readString()));
		setComunicaciones_c(validate(in.readString()));
		setPreciosmineaton_c(validate(in.readString()));
		setManiobraeaton_c(validate(in.readString()));
		setDescuento1_c(validate(in.readString()));
		setDescuento2_c(validate(in.readString()));
		setDescuento3_c(validate(in.readString()));
		setDescuento4_c(validate(in.readString()));
		setDescuento5_c(validate(in.readString()));
		setDescuento6_c(validate(in.readString()));
		setDescuento7_c(validate(in.readString()));
		setDescuento8_c(validate(in.readString()));
		setDescuento9_c(validate(in.readString()));
		setDescuento10_c(validate(in.readString()));
		setBonosespeciales_c(validate(in.readString()));
		setBonificacioncompra_c(validate(in.readString()));
		setManiobraeatonanual_c(validate(in.readString()));
		setBonificacionleviton_c(validate(in.readString()));
		setMeta1_c(validate(in.readString()));
		setMeta2_c(validate(in.readString()));
		setMeta3_c(validate(in.readString()));
		setMeta4_c(validate(in.readString()));
		setBonificacion1_c(validate(in.readString()));
		setBonificacion2_c(validate(in.readString()));
		setBonificacion3_c(validate(in.readString()));
		setBonificacion4_c(validate(in.readString()));
		setDespachoremsion_c(validate(in.readString()));
		setNocobro_c(validate(in.readString()));
		setEstrategia1_c(validate(in.readString()));
		setEstrategia2_c(validate(in.readString()));
		setEstrategia3_c(validate(in.readString()));
		setEstrategia4_c(validate(in.readString()));
		setFechaempresa_c(validate(in.readString()));
		setDiamarca_c(validate(in.readString()));
		setExhibidor_c(validate(in.readString()));
		setApoya_c(validate(in.readString()));
		setPlacaaniversario_c(validate(in.readString()));
		setCertificaciones_c(validate(in.readString()));
		setFechamarca_c(validate(in.readString()));
		setEntregaexhibidor1_c(validate(in.readString()));
		setEntregaexhibidor2_c(validate(in.readString()));
		setEntregaexhibidor3_c(validate(in.readString()));
		setFechaapoya_c(validate(in.readString()));
		setFechaplaca_c(validate(in.readString()));
		setOtrasestrategias_c(validate(in.readString()));
		setImagen1_c(validate(in.readString()));
		setImagen2_c(validate(in.readString()));
		setImagen3_c(validate(in.readString()));
		setActa1_c(validate(in.readString()));
		setActa2_c(validate(in.readString()));
		setActa3_c(validate(in.readString()));
		setPieza_c(validate(in.readString()));
		setFechaapoya2_c(validate(in.readString()));
		setPieza2_c(validate(in.readString()));
		setFechafacturacion_c(validate(in.readString()));
		setCorreotransporte_c(validate(in.readString()));
		setLaumayer2_c(validate(in.readString()));
		setVentasactual_c(validate(in.readString()));
		setVentasanterior_c(validate(in.readString()));
		// setNumeroalianzas_c(validate(in.readString()));
		setCupocr_c(validate(in.readString()));
		setFacturacionmes_c(validate(in.readString()));
		setCupodisponible_c(validate(in.readString()));
		setNit_c(validate(in.readString()));
		setFacturaciondiara_c(validate(in.readString()));
		setFacturacionautorizada_c(validate(in.readString()));
		setCod_alterno_c(validate(in.readString()));
		setTotalcartera_c(validate(in.readString()));
		setRemesa_c(validate(in.readString()));
		setDestino_c(validate(in.readString()));
		setNombredestinatario_c(validate(in.readString()));
		setUnidades_c(validate(in.readString()));
		setDocumento_c(validate(in.readString()));
		setNombredestinatario2_c(validate(in.readString()));
		setDestino2_c(validate(in.readString()));
		setMotivo_c(validate(in.readString()));
		setFacturacionnoautorizada_c(validate(in.readString()));
		setPorcentaje_cumplimiento_c(validate(in.readString()));
		setFecha_despacho_c(validate(in.readString()));
		setPrue_c(validate(in.readString()));
		setPrueba_c(validate(in.readString()));
		setPreuba_c(validate(in.readString()));
		setAssigned_user_name(validate(in.readString()));
		setEmail_address(validate(in.readString()));
		setDescuentocomercial2_c(validate(in.readString()));
		setDkc2_c(validate(in.readString()));
		setDescuento11_c(validate(in.readString()));
		setOemxenergy_c(validate(in.readString()));
		setActivos_actual_c(validate(in.readString()));
		setActivos_anterior_c(validate(in.readString()));
		setClase_c(validate(in.readString()));
		setPasivos_actual_c(validate(in.readString()));
		setPasivos_anterior_c(validate(in.readString()));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getId());
		dest.writeString(getName());
		dest.writeString(getDate_entered());
		dest.writeString(getDate_modified());
		dest.writeString(getModified_user_id());
		dest.writeString(getCreated_by());
		dest.writeString(getDescription());
		dest.writeString(getDeleted());
		dest.writeString(getAssigned_user_id());
		dest.writeString(getAccount_type());
		dest.writeString(getIndustry());
		dest.writeString(getAnnual_revenue());
		dest.writeString(getPhone_fax());
		dest.writeString(getBilling_address_street());
		dest.writeString(getBilling_address_city());
		dest.writeString(getBilling_address_state());
		dest.writeString(getBilling_address_postalcode());
		dest.writeString(getBilling_address_country());
		dest.writeString(getRating());
		dest.writeString(getPhone_office());
		dest.writeString(getPhone_alternate());
		dest.writeString(getWebsite());
		dest.writeString(getOwnership());
		dest.writeString(getEmployees());
		dest.writeString(getTicker_symbol());
		dest.writeString(getShipping_address_street());
		dest.writeString(getShipping_address_city());
		dest.writeString(getShipping_address_state());
		dest.writeString(getShipping_address_postalcode());
		dest.writeString(getShipping_address_country());
		dest.writeString(getParent_id());
		dest.writeString(getSic_code());
		dest.writeString(getCampaign_id());
		dest.writeString(getId_c());
		dest.writeString(getCanal_c());
		dest.writeString(getSector_c());
		dest.writeString(getExtension1_c());
		dest.writeString(getExtension2_c());
		dest.writeString(getCelular_c());
		dest.writeString(getDireccion_c());
		dest.writeString(getDepartamento_c());
		dest.writeString(getMunicipio_c());
		dest.writeString(getZona_c());
		dest.writeString(getUen_c());
		dest.writeString(getDescuentocomercial_c());
		dest.writeString(getPresupuestoanual_c());
		dest.writeString(getCondpago_c());
		dest.writeString(getPlpago_c());
		dest.writeString(getPrompago_c());
		dest.writeString(getCarteravencida_c());
		dest.writeString(getCarteravencer_c());
		dest.writeString(getGrupo_objetivo_c());
		dest.writeString(getSegmento_c());
		dest.writeString(getEstado_c());
		dest.writeString(getOrigencuenta_c());
		// dest.writeString(getAlianzasestrategicas_c());
		dest.writeString(getAutomatizacionmoeller_c());
		dest.writeString(getEnerlux_c());
		dest.writeString(getDelta_c());
		dest.writeString(getDkc_c());
		dest.writeString(getLeviton_c());
		dest.writeString(getWvcai_c());
		dest.writeString(getWohner_c());
		dest.writeString(getComunicaciones_c());
		dest.writeString(getPreciosmineaton_c());
		dest.writeString(getManiobraeaton_c());
		dest.writeString(getDescuento1_c());
		dest.writeString(getDescuento2_c());
		dest.writeString(getDescuento3_c());
		dest.writeString(getDescuento4_c());
		dest.writeString(getDescuento5_c());
		dest.writeString(getDescuento6_c());
		dest.writeString(getDescuento7_c());
		dest.writeString(getDescuento8_c());
		dest.writeString(getDescuento9_c());
		dest.writeString(getDescuento10_c());
		dest.writeString(getBonosespeciales_c());
		dest.writeString(getBonificacioncompra_c());
		dest.writeString(getManiobraeatonanual_c());
		dest.writeString(getBonificacionleviton_c());
		dest.writeString(getMeta1_c());
		dest.writeString(getMeta2_c());
		dest.writeString(getMeta3_c());
		dest.writeString(getMeta4_c());
		dest.writeString(getBonificacion1_c());
		dest.writeString(getBonificacion2_c());
		dest.writeString(getBonificacion3_c());
		dest.writeString(getBonificacion4_c());
		dest.writeString(getDespachoremsion_c());
		dest.writeString(getNocobro_c());
		dest.writeString(getEstrategia1_c());
		dest.writeString(getEstrategia2_c());
		dest.writeString(getEstrategia3_c());
		dest.writeString(getEstrategia4_c());
		dest.writeString(getFechaempresa_c());
		dest.writeString(getDiamarca_c());
		dest.writeString(getExhibidor_c());
		dest.writeString(getApoya_c());
		dest.writeString(getPlacaaniversario_c());
		dest.writeString(getCertificaciones_c());
		dest.writeString(getFechamarca_c());
		dest.writeString(getEntregaexhibidor1_c());
		dest.writeString(getEntregaexhibidor2_c());
		dest.writeString(getEntregaexhibidor3_c());
		dest.writeString(getFechaapoya_c());
		dest.writeString(getFechaplaca_c());
		dest.writeString(getOtrasestrategias_c());
		dest.writeString(getImagen1_c());
		dest.writeString(getImagen2_c());
		dest.writeString(getImagen3_c());
		dest.writeString(getActa1_c());
		dest.writeString(getActa2_c());
		dest.writeString(getActa3_c());
		dest.writeString(getPieza_c());
		dest.writeString(getFechaapoya2_c());
		dest.writeString(getPieza2_c());
		dest.writeString(getFechafacturacion_c());
		dest.writeString(getCorreotransporte_c());
		dest.writeString(getLaumayer2_c());
		dest.writeString(getVentasactual_c());
		dest.writeString(getVentasanterior_c());
		// dest.writeString(getNumeroalianzas_c());
		dest.writeString(getCupocr_c());
		dest.writeString(getFacturacionmes_c());
		dest.writeString(getCupodisponible_c());
		dest.writeString(getNit_c());
		dest.writeString(getFacturaciondiara_c());
		dest.writeString(getFacturacionautorizada_c());
		dest.writeString(getCod_alterno_c());
		dest.writeString(getTotalcartera_c());
		dest.writeString(getRemesa_c());
		dest.writeString(getDestino_c());
		dest.writeString(getNombredestinatario_c());
		dest.writeString(getUnidades_c());
		dest.writeString(getDocumento_c());
		dest.writeString(getNombredestinatario2_c());
		dest.writeString(getDestino2_c());
		dest.writeString(getMotivo_c());
		dest.writeString(getFacturacionnoautorizada_c());
		dest.writeString(getPorcentaje_cumplimiento_c());
		dest.writeString(getFecha_despacho_c());
		dest.writeString(getPrue_c());
		dest.writeString(getPrueba_c());
		dest.writeString(getPreuba_c());
		dest.writeString(getAssigned_user_name());
		dest.writeString(getEmail_address());
		dest.writeString(getDescuentocomercial2_c());
		dest.writeString(getDkc2_c());
		dest.writeString(getDescuento11_c());
		dest.writeString(getOemxenergy_c());
		dest.writeString(getActivos_actual_c());
		dest.writeString(getActivos_anterior_c());
		dest.writeString(getClase_c());
		dest.writeString(getPasivos_actual_c());
		dest.writeString(getPasivos_anterior_c());
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

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAnnual_revenue() {
		return annual_revenue;
	}

	public void setAnnual_revenue(String annual_revenue) {
		this.annual_revenue = annual_revenue;
	}

	public String getPhone_fax() {
		return phone_fax;
	}

	public void setPhone_fax(String phone_fax) {
		this.phone_fax = phone_fax;
	}

	public String getBilling_address_street() {
		return billing_address_street;
	}

	public void setBilling_address_street(String billing_address_street) {
		this.billing_address_street = billing_address_street;
	}

	public String getBilling_address_city() {
		return billing_address_city;
	}

	public void setBilling_address_city(String billing_address_city) {
		this.billing_address_city = billing_address_city;
	}

	public String getBilling_address_state() {
		return billing_address_state;
	}

	public void setBilling_address_state(String billing_address_state) {
		this.billing_address_state = billing_address_state;
	}

	public String getBilling_address_postalcode() {
		return billing_address_postalcode;
	}

	public void setBilling_address_postalcode(String billing_address_postalcode) {
		this.billing_address_postalcode = billing_address_postalcode;
	}

	public String getBilling_address_country() {
		return billing_address_country;
	}

	public void setBilling_address_country(String billing_address_country) {
		this.billing_address_country = billing_address_country;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPhone_office() {
		return phone_office;
	}

	public void setPhone_office(String phone_office) {
		this.phone_office = phone_office;
	}

	public String getPhone_alternate() {
		return phone_alternate;
	}

	public void setPhone_alternate(String phone_alternate) {
		this.phone_alternate = phone_alternate;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public String getTicker_symbol() {
		return ticker_symbol;
	}

	public void setTicker_symbol(String ticker_symbol) {
		this.ticker_symbol = ticker_symbol;
	}

	public String getShipping_address_street() {
		return shipping_address_street;
	}

	public void setShipping_address_street(String shipping_address_street) {
		this.shipping_address_street = shipping_address_street;
	}

	public String getShipping_address_city() {
		return shipping_address_city;
	}

	public void setShipping_address_city(String shipping_address_city) {
		this.shipping_address_city = shipping_address_city;
	}

	public String getShipping_address_state() {
		return shipping_address_state;
	}

	public void setShipping_address_state(String shipping_address_state) {
		this.shipping_address_state = shipping_address_state;
	}

	public String getShipping_address_postalcode() {
		return shipping_address_postalcode;
	}

	public void setShipping_address_postalcode(String shipping_address_postalcode) {
		this.shipping_address_postalcode = shipping_address_postalcode;
	}

	public String getShipping_address_country() {
		return shipping_address_country;
	}

	public void setShipping_address_country(String shipping_address_country) {
		this.shipping_address_country = shipping_address_country;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getSic_code() {
		return sic_code;
	}

	public void setSic_code(String sic_code) {
		this.sic_code = sic_code;
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

	public String getCanal_c() {
		return canal_c;
	}

	public void setCanal_c(String canal_c) {
		this.canal_c = canal_c;
	}

	public String getSector_c() {
		return sector_c;
	}

	public void setSector_c(String sector_c) {
		this.sector_c = sector_c;
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

	public String getCelular_c() {
		return celular_c;
	}

	public void setCelular_c(String celular_c) {
		this.celular_c = celular_c;
	}

	public String getDireccion_c() {
		return direccion_c;
	}

	public void setDireccion_c(String direccion_c) {
		this.direccion_c = direccion_c;
	}

	public String getDepartamento_c() {
		return departamento_c;
	}

	public void setDepartamento_c(String departamento_c) {
		this.departamento_c = departamento_c;
	}

	public String getMunicipio_c() {
		return municipio_c;
	}

	public void setMunicipio_c(String municipio_c) {
		this.municipio_c = municipio_c;
	}

	public String getZona_c() {
		return zona_c;
	}

	public void setZona_c(String zona_c) {
		this.zona_c = zona_c;
	}

	public String getUen_c() {
		return uen_c;
	}

	public void setUen_c(String uen_c) {
		this.uen_c = uen_c;
	}

	public String getDescuentocomercial_c() {
		return descuentocomercial_c;
	}

	public void setDescuentocomercial_c(String descuentocomercial_c) {
		this.descuentocomercial_c = descuentocomercial_c;
	}

	public String getPresupuestoanual_c() {
		return presupuestoanual_c;
	}

	public void setPresupuestoanual_c(String presupuestoanual_c) {
		this.presupuestoanual_c = presupuestoanual_c;
	}

	public String getCondpago_c() {
		return condpago_c;
	}

	public void setCondpago_c(String condpago_c) {
		this.condpago_c = condpago_c;
	}

	public String getPlpago_c() {
		return plpago_c;
	}

	public void setPlpago_c(String plpago_c) {
		this.plpago_c = plpago_c;
	}

	public String getPrompago_c() {
		return prompago_c;
	}

	public void setPrompago_c(String prompago_c) {
		this.prompago_c = prompago_c;
	}

	public String getCarteravencida_c() {
		return carteravencida_c;
	}

	public void setCarteravencida_c(String carteravencida_c) {
		this.carteravencida_c = carteravencida_c;
	}

	public String getCarteravencer_c() {
		return carteravencer_c;
	}

	public void setCarteravencer_c(String carteravencer_c) {
		this.carteravencer_c = carteravencer_c;
	}

	public String getGrupo_objetivo_c() {
		return grupo_objetivo_c;
	}

	public void setGrupo_objetivo_c(String grupo_objetivo_c) {
		this.grupo_objetivo_c = grupo_objetivo_c;
	}

	public String getSegmento_c() {
		return segmento_c;
	}

	public void setSegmento_c(String segmento_c) {
		this.segmento_c = segmento_c;
	}

	public String getEstado_c() {
		return estado_c;
	}

	public void setEstado_c(String estado_c) {
		this.estado_c = estado_c;
	}

	public String getOrigencuenta_c() {
		return origencuenta_c;
	}

	public void setOrigencuenta_c(String origencuenta_c) {
		this.origencuenta_c = origencuenta_c;
	}

	/*
	 * public String getAlianzasestrategicas_c() { return
	 * alianzasestrategicas_c; }
	 * 
	 * public void setAlianzasestrategicas_c(String alianzasestrategicas_c) {
	 * this.alianzasestrategicas_c = alianzasestrategicas_c; }
	 */

	public String getAutomatizacionmoeller_c() {
		return automatizacionmoeller_c;
	}

	public void setAutomatizacionmoeller_c(String automatizacionmoeller_c) {
		this.automatizacionmoeller_c = automatizacionmoeller_c;
	}

	public String getEnerlux_c() {
		return enerlux_c;
	}

	public void setEnerlux_c(String enerlux_c) {
		this.enerlux_c = enerlux_c;
	}

	public String getDelta_c() {
		return delta_c;
	}

	public void setDelta_c(String delta_c) {
		this.delta_c = delta_c;
	}

	public String getDkc_c() {
		return dkc_c;
	}

	public void setDkc_c(String dkc_c) {
		this.dkc_c = dkc_c;
	}

	public String getLeviton_c() {
		return leviton_c;
	}

	public void setLeviton_c(String leviton_c) {
		this.leviton_c = leviton_c;
	}

	public String getWvcai_c() {
		return wvcai_c;
	}

	public void setWvcai_c(String wvcai_c) {
		this.wvcai_c = wvcai_c;
	}

	public String getWohner_c() {
		return wohner_c;
	}

	public void setWohner_c(String wohner_c) {
		this.wohner_c = wohner_c;
	}

	public String getComunicaciones_c() {
		return comunicaciones_c;
	}

	public void setComunicaciones_c(String comunicaciones_c) {
		this.comunicaciones_c = comunicaciones_c;
	}

	public String getPreciosmineaton_c() {
		return preciosmineaton_c;
	}

	public void setPreciosmineaton_c(String preciosmineaton_c) {
		this.preciosmineaton_c = preciosmineaton_c;
	}

	public String getManiobraeaton_c() {
		return maniobraeaton_c;
	}

	public void setManiobraeaton_c(String maniobraeaton_c) {
		this.maniobraeaton_c = maniobraeaton_c;
	}

	public String getDescuento1_c() {
		return descuento1_c;
	}

	public void setDescuento1_c(String descuento1_c) {
		this.descuento1_c = descuento1_c;
	}

	public String getDescuento2_c() {
		return descuento2_c;
	}

	public void setDescuento2_c(String descuento2_c) {
		this.descuento2_c = descuento2_c;
	}

	public String getDescuento3_c() {
		return descuento3_c;
	}

	public void setDescuento3_c(String descuento3_c) {
		this.descuento3_c = descuento3_c;
	}

	public String getDescuento4_c() {
		return descuento4_c;
	}

	public void setDescuento4_c(String descuento4_c) {
		this.descuento4_c = descuento4_c;
	}

	public String getDescuento5_c() {
		return descuento5_c;
	}

	public void setDescuento5_c(String descuento5_c) {
		this.descuento5_c = descuento5_c;
	}

	public String getDescuento6_c() {
		return descuento6_c;
	}

	public void setDescuento6_c(String descuento6_c) {
		this.descuento6_c = descuento6_c;
	}

	public String getDescuento7_c() {
		return descuento7_c;
	}

	public void setDescuento7_c(String descuento7_c) {
		this.descuento7_c = descuento7_c;
	}

	public String getDescuento8_c() {
		return descuento8_c;
	}

	public void setDescuento8_c(String descuento8_c) {
		this.descuento8_c = descuento8_c;
	}

	public String getDescuento9_c() {
		return descuento9_c;
	}

	public void setDescuento9_c(String descuento9_c) {
		this.descuento9_c = descuento9_c;
	}

	public String getDescuento10_c() {
		return descuento10_c;
	}

	public void setDescuento10_c(String descuento10_c) {
		this.descuento10_c = descuento10_c;
	}

	public String getBonosespeciales_c() {
		return bonosespeciales_c;
	}

	public void setBonosespeciales_c(String bonosespeciales_c) {
		this.bonosespeciales_c = bonosespeciales_c;
	}

	public String getBonificacioncompra_c() {
		return bonificacioncompra_c;
	}

	public void setBonificacioncompra_c(String bonificacioncompra_c) {
		this.bonificacioncompra_c = bonificacioncompra_c;
	}

	public String getManiobraeatonanual_c() {
		return maniobraeatonanual_c;
	}

	public void setManiobraeatonanual_c(String maniobraeatonanual_c) {
		this.maniobraeatonanual_c = maniobraeatonanual_c;
	}

	public String getBonificacionleviton_c() {
		return bonificacionleviton_c;
	}

	public void setBonificacionleviton_c(String bonificacionleviton_c) {
		this.bonificacionleviton_c = bonificacionleviton_c;
	}

	public String getMeta1_c() {
		return meta1_c;
	}

	public void setMeta1_c(String meta1_c) {
		this.meta1_c = meta1_c;
	}

	public String getMeta2_c() {
		return meta2_c;
	}

	public void setMeta2_c(String meta2_c) {
		this.meta2_c = meta2_c;
	}

	public String getMeta3_c() {
		return meta3_c;
	}

	public void setMeta3_c(String meta3_c) {
		this.meta3_c = meta3_c;
	}

	public String getMeta4_c() {
		return meta4_c;
	}

	public void setMeta4_c(String meta4_c) {
		this.meta4_c = meta4_c;
	}

	public String getBonificacion1_c() {
		return bonificacion1_c;
	}

	public void setBonificacion1_c(String bonificacion1_c) {
		this.bonificacion1_c = bonificacion1_c;
	}

	public String getBonificacion2_c() {
		return bonificacion2_c;
	}

	public void setBonificacion2_c(String bonificacion2_c) {
		this.bonificacion2_c = bonificacion2_c;
	}

	public String getBonificacion3_c() {
		return bonificacion3_c;
	}

	public void setBonificacion3_c(String bonificacion3_c) {
		this.bonificacion3_c = bonificacion3_c;
	}

	public String getBonificacion4_c() {
		return bonificacion4_c;
	}

	public void setBonificacion4_c(String bonificacion4_c) {
		this.bonificacion4_c = bonificacion4_c;
	}

	public String getDespachoremsion_c() {
		return despachoremsion_c;
	}

	public void setDespachoremsion_c(String despachoremsion_c) {
		this.despachoremsion_c = despachoremsion_c;
	}

	public String getNocobro_c() {
		return nocobro_c;
	}

	public void setNocobro_c(String nocobro_c) {
		this.nocobro_c = nocobro_c;
	}

	public String getEstrategia1_c() {
		return estrategia1_c;
	}

	public void setEstrategia1_c(String estrategia1_c) {
		this.estrategia1_c = estrategia1_c;
	}

	public String getEstrategia2_c() {
		return estrategia2_c;
	}

	public void setEstrategia2_c(String estrategia2_c) {
		this.estrategia2_c = estrategia2_c;
	}

	public String getEstrategia3_c() {
		return estrategia3_c;
	}

	public void setEstrategia3_c(String estrategia3_c) {
		this.estrategia3_c = estrategia3_c;
	}

	public String getEstrategia4_c() {
		return estrategia4_c;
	}

	public void setEstrategia4_c(String estrategia4_c) {
		this.estrategia4_c = estrategia4_c;
	}

	public String getFechaempresa_c() {
		return fechaempresa_c;
	}

	public void setFechaempresa_c(String fechaempresa_c) {
		this.fechaempresa_c = fechaempresa_c;
	}

	public String getDiamarca_c() {
		return diamarca_c;
	}

	public void setDiamarca_c(String diamarca_c) {
		this.diamarca_c = diamarca_c;
	}

	public String getExhibidor_c() {
		return exhibidor_c;
	}

	public void setExhibidor_c(String exhibidor_c) {
		this.exhibidor_c = exhibidor_c;
	}

	public String getApoya_c() {
		return apoya_c;
	}

	public void setApoya_c(String apoya_c) {
		this.apoya_c = apoya_c;
	}

	public String getPlacaaniversario_c() {
		return placaaniversario_c;
	}

	public void setPlacaaniversario_c(String placaaniversario_c) {
		this.placaaniversario_c = placaaniversario_c;
	}

	public String getCertificaciones_c() {
		return certificaciones_c;
	}

	public void setCertificaciones_c(String certificaciones_c) {
		this.certificaciones_c = certificaciones_c;
	}

	public String getFechamarca_c() {
		return fechamarca_c;
	}

	public void setFechamarca_c(String fechamarca_c) {
		this.fechamarca_c = fechamarca_c;
	}

	public String getEntregaexhibidor1_c() {
		return entregaexhibidor1_c;
	}

	public void setEntregaexhibidor1_c(String entregaexhibidor1_c) {
		this.entregaexhibidor1_c = entregaexhibidor1_c;
	}

	public String getEntregaexhibidor2_c() {
		return entregaexhibidor2_c;
	}

	public void setEntregaexhibidor2_c(String entregaexhibidor2_c) {
		this.entregaexhibidor2_c = entregaexhibidor2_c;
	}

	public String getEntregaexhibidor3_c() {
		return entregaexhibidor3_c;
	}

	public void setEntregaexhibidor3_c(String entregaexhibidor3_c) {
		this.entregaexhibidor3_c = entregaexhibidor3_c;
	}

	public String getFechaapoya_c() {
		return fechaapoya_c;
	}

	public void setFechaapoya_c(String fechaapoya_c) {
		this.fechaapoya_c = fechaapoya_c;
	}

	public String getFechaplaca_c() {
		return fechaplaca_c;
	}

	public void setFechaplaca_c(String fechaplaca_c) {
		this.fechaplaca_c = fechaplaca_c;
	}

	public String getOtrasestrategias_c() {
		return otrasestrategias_c;
	}

	public void setOtrasestrategias_c(String otrasestrategias_c) {
		this.otrasestrategias_c = otrasestrategias_c;
	}

	public String getImagen1_c() {
		return imagen1_c;
	}

	public void setImagen1_c(String imagen1_c) {
		this.imagen1_c = imagen1_c;
	}

	public String getImagen2_c() {
		return imagen2_c;
	}

	public void setImagen2_c(String imagen2_c) {
		this.imagen2_c = imagen2_c;
	}

	public String getImagen3_c() {
		return imagen3_c;
	}

	public void setImagen3_c(String imagen3_c) {
		this.imagen3_c = imagen3_c;
	}

	public String getActa1_c() {
		return acta1_c;
	}

	public void setActa1_c(String acta1_c) {
		this.acta1_c = acta1_c;
	}

	public String getActa2_c() {
		return acta2_c;
	}

	public void setActa2_c(String acta2_c) {
		this.acta2_c = acta2_c;
	}

	public String getActa3_c() {
		return acta3_c;
	}

	public void setActa3_c(String acta3_c) {
		this.acta3_c = acta3_c;
	}

	public String getPieza_c() {
		return pieza_c;
	}

	public void setPieza_c(String pieza_c) {
		this.pieza_c = pieza_c;
	}

	public String getFechaapoya2_c() {
		return fechaapoya2_c;
	}

	public void setFechaapoya2_c(String fechaapoya2_c) {
		this.fechaapoya2_c = fechaapoya2_c;
	}

	public String getPieza2_c() {
		return pieza2_c;
	}

	public void setPieza2_c(String pieza2_c) {
		this.pieza2_c = pieza2_c;
	}

	public String getFechafacturacion_c() {
		return fechafacturacion_c;
	}

	public void setFechafacturacion_c(String fechafacturacion_c) {
		this.fechafacturacion_c = fechafacturacion_c;
	}

	public String getCorreotransporte_c() {
		return correotransporte_c;
	}

	public void setCorreotransporte_c(String correotransporte_c) {
		this.correotransporte_c = correotransporte_c;
	}

	public String getLaumayer2_c() {
		return laumayer2_c;
	}

	public void setLaumayer2_c(String laumayer2_c) {
		this.laumayer2_c = laumayer2_c;
	}

	public String getVentasactual_c() {
		return ventasactual_c;
	}

	public void setVentasactual_c(String ventasactual_c) {
		this.ventasactual_c = ventasactual_c;
	}

	public String getVentasanterior_c() {
		return ventasanterior_c;
	}

	public void setVentasanterior_c(String ventasanterior_c) {
		this.ventasanterior_c = ventasanterior_c;
	}

	/*
	 * public String getNumeroalianzas_c() { return numeroalianzas_c; }
	 * 
	 * public void setNumeroalianzas_c(String numeroalianzas_c) {
	 * this.numeroalianzas_c = numeroalianzas_c; }
	 */

	public String getCupocr_c() {
		return cupocr_c;
	}

	public void setCupocr_c(String cupocr_c) {
		this.cupocr_c = cupocr_c;
	}

	public String getFacturacionmes_c() {
		return facturacionmes_c;
	}

	public void setFacturacionmes_c(String facturacionmes_c) {
		this.facturacionmes_c = facturacionmes_c;
	}

	public String getCupodisponible_c() {
		return cupodisponible_c;
	}

	public void setCupodisponible_c(String cupodisponible_c) {
		this.cupodisponible_c = cupodisponible_c;
	}

	public String getNit_c() {
		return nit_c;
	}

	public void setNit_c(String nit_c) {
		this.nit_c = nit_c;
	}

	public String getFacturaciondiara_c() {
		return facturaciondiara_c;
	}

	public void setFacturaciondiara_c(String facturaciondiara_c) {
		this.facturaciondiara_c = facturaciondiara_c;
	}

	public String getFacturacionautorizada_c() {
		return facturacionautorizada_c;
	}

	public void setFacturacionautorizada_c(String facturacionautorizada_c) {
		this.facturacionautorizada_c = facturacionautorizada_c;
	}

	public String getCod_alterno_c() {
		return cod_alterno_c;
	}

	public void setCod_alterno_c(String cod_alterno_c) {
		this.cod_alterno_c = cod_alterno_c;
	}

	public String getTotalcartera_c() {
		return totalcartera_c;
	}

	public void setTotalcartera_c(String totalcartera_c) {
		this.totalcartera_c = totalcartera_c;
	}

	public String getRemesa_c() {
		return remesa_c;
	}

	public void setRemesa_c(String remesa_c) {
		this.remesa_c = remesa_c;
	}

	public String getDestino_c() {
		return destino_c;
	}

	public void setDestino_c(String destino_c) {
		this.destino_c = destino_c;
	}

	public String getNombredestinatario_c() {
		return nombredestinatario_c;
	}

	public void setNombredestinatario_c(String nombredestinatario_c) {
		this.nombredestinatario_c = nombredestinatario_c;
	}

	public String getUnidades_c() {
		return unidades_c;
	}

	public void setUnidades_c(String unidades_c) {
		this.unidades_c = unidades_c;
	}

	public String getDocumento_c() {
		return documento_c;
	}

	public void setDocumento_c(String documento_c) {
		this.documento_c = documento_c;
	}

	public String getNombredestinatario2_c() {
		return nombredestinatario2_c;
	}

	public void setNombredestinatario2_c(String nombredestinatario2_c) {
		this.nombredestinatario2_c = nombredestinatario2_c;
	}

	public String getDestino2_c() {
		return destino2_c;
	}

	public void setDestino2_c(String destino2_c) {
		this.destino2_c = destino2_c;
	}

	public String getMotivo_c() {
		return motivo_c;
	}

	public void setMotivo_c(String motivo_c) {
		this.motivo_c = motivo_c;
	}

	public String getFacturacionnoautorizada_c() {
		return facturacionnoautorizada_c;
	}

	public void setFacturacionnoautorizada_c(String facturacionnoautorizada_c) {
		this.facturacionnoautorizada_c = facturacionnoautorizada_c;
	}

	public String getPorcentaje_cumplimiento_c() {
		return porcentaje_cumplimiento_c;
	}

	public void setPorcentaje_cumplimiento_c(String porcentaje_cumplimiento_c) {
		this.porcentaje_cumplimiento_c = porcentaje_cumplimiento_c;
	}

	public String getFecha_despacho_c() {
		return fecha_despacho_c;
	}

	public void setFecha_despacho_c(String fecha_despacho_c) {
		this.fecha_despacho_c = fecha_despacho_c;
	}

	public String getPrue_c() {
		return prue_c;
	}

	public void setPrue_c(String prue_c) {
		this.prue_c = prue_c;
	}

	public String getPrueba_c() {
		return prueba_c;
	}

	public void setPrueba_c(String prueba_c) {
		this.prueba_c = prueba_c;
	}

	public String getPreuba_c() {
		return preuba_c;
	}

	public void setPreuba_c(String preuba_c) {
		this.preuba_c = preuba_c;
	}

	public String getAssigned_user_name() {
		return assigned_user_name;
	}

	public void setAssigned_user_name(String assigned_user_name) {
		this.assigned_user_name = assigned_user_name;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getDescuentocomercial2_c() {
		return descuentocomercial2_c;
	}

	public void setDescuentocomercial2_c(String descuentocomercial2_c) {
		this.descuentocomercial2_c = descuentocomercial2_c;
	}

	public String getDkc2_c() {
		return dkc2_c;
	}

	public void setDkc2_c(String dkc2_c) {
		this.dkc2_c = dkc2_c;
	}

	public String getDescuento11_c() {
		return descuento11_c;
	}

	public void setDescuento11_c(String descuento11_c) {
		this.descuento11_c = descuento11_c;
	}

	public String getOemxenergy_c() {
		return oemxenergy_c;
	}

	public void setOemxenergy_c(String oemxenergy_c) {
		this.oemxenergy_c = oemxenergy_c;
	}

	public String getActivos_actual_c() {
		return activos_actual_c;
	}

	public void setActivos_actual_c(String activos_actual_c) {
		this.activos_actual_c = activos_actual_c;
	}

	public String getActivos_anterior_c() {
		return activos_anterior_c;
	}

	public void setActivos_anterior_c(String activos_anterior_c) {
		this.activos_anterior_c = activos_anterior_c;
	}

	public String getClase_c() {
		return clase_c;
	}

	public void setClase_c(String clase_c) {
		this.clase_c = clase_c;
	}

	public String getPasivos_actual_c() {
		return pasivos_actual_c;
	}

	public void setPasivos_actual_c(String pasivos_actual_c) {
		this.pasivos_actual_c = pasivos_actual_c;
	}

	public String getPasivos_anterior_c() {
		return pasivos_anterior_c;
	}

	public void setPasivos_anterior_c(String pasivos_anterior_c) {
		this.pasivos_anterior_c = pasivos_anterior_c;
	}

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
}