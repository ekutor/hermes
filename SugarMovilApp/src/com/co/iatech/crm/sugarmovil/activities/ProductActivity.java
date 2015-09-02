package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.ProductoDetalle;


public class ProductActivity extends AppCompatActivity {


    /**
     * Debug.
     */
    private static final String TAG = "ProductActivity";

    /**
     * Tasks.
     */
    private GetProductTask mTareaObtenerProducto = null;

    /**
     * Member Variables.
     */
    private String mIdProducto;
    private ProductoDetalle mProductoDetalle;

    /**
     * UI References.
     */
    private Toolbar mProductoToolbar;
    private ImageButton mImageButtonEdit;
    private LinearLayout mLayoutContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        mIdProducto = intent.getStringExtra(Info.ID_PRODUCTO.name());
        Log.d(TAG, "Id producto " + mIdProducto);

        // Main Toolbar
        mProductoToolbar = (Toolbar) findViewById(R.id.toolbar_product);
        setSupportActionBar(mProductoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);

        // Contenido
        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);

        //Eventos
        mImageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Editar producto ");
                // Edit Producto Activity
                // TODO
            }
        });

        // Tarea obtener producto
        mTareaObtenerProducto = new GetProductTask();
        mTareaObtenerProducto.execute(String.valueOf(mIdProducto));
    }

    public void ponerValores(ProductoDetalle productoDetalle) {
        TextView valorCodigo = (TextView) findViewById(R.id.valor_codigo);
        valorCodigo.setText(productoDetalle.getCodigo_c());
        TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
        valorNombre.setText(productoDetalle.getName());
        TextView valorReferencia = (TextView) findViewById(R.id.valor_referencia);
        valorReferencia.setText(productoDetalle.getReferencia_c());
        TextView valorMarca = (TextView) findViewById(R.id.valor_marca);
        valorMarca.setText(productoDetalle.getMarca_c());
        TextView valorEnInventario = (TextView) findViewById(R.id.valor_en_inventario);
        valorEnInventario.setText(productoDetalle.getEn_inventario_c());
        TextView valorPrecioPesos = (TextView) findViewById(R.id.valor_precio_pesos);
        valorPrecioPesos.setText(productoDetalle.getPrecio1_c());
        TextView valorPrecioDolares = (TextView) findViewById(R.id.valor_precio_dolares);
        valorPrecioDolares.setText(productoDetalle.getPrecio2_c());
        TextView valorGrupo = (TextView) findViewById(R.id.valor_grupo);
        valorGrupo.setText(productoDetalle.getGrupo_c());
    }

    /**
     * Representa una tarea asincrona de obtencion de producto.
     */
    public class GetProductTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ProductActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando información de producto...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idProducto = params[0];

                // Respuesta
                String resultado = null;

                // Intento de obtener producto
                ControlConnection.addHeader("idProducto", idProducto);
                resultado  = ControlConnection.getInfo(TypeInfoServer.getProducto);

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                if(jArr.length() > 0) {
                    JSONObject obj = jArr.getJSONObject(0);
                     mProductoDetalle = new ProductoDetalle(obj);
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Producto Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerProducto = null;
            progressDialog.dismiss();

            if (success) {
                ponerValores(mProductoDetalle);
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerProducto = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
