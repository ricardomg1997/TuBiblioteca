package co.com.uco.tubiblioteca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.uco.tubiblioteca.R;
import co.com.uco.tubiblioteca.entities.Book;

public class LibroAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private List<Book> listaLibrosOut;
    private List<Book> listaLibrosIn;

    public LibroAdapter(Context context, List<Book> listaLibros) {
        this.context = context;
        listaLibrosOut = listaLibros;
        listaLibrosIn = listaLibros;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaLibrosOut.size();
    }

    @Override
    public Object getItem(int i) {
        return listaLibrosOut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.libro_prestado_layout_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.txtNombreLibro.setText(listaLibrosOut.get(i).getNombre());
        holder.txtDescripcionLibro.setText(listaLibrosOut.get(i).getLibro());
        return convertView;
    }



    class ViewHolder{
        @BindView(R.id.txtNombreLibro)
        TextView txtNombreLibro;
        @BindView(R.id.txtDescripcionLibro)
        TextView txtDescripcionLibro;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
