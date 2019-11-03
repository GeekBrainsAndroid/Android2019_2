package ru.geekbrains.contacts;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder> {

    private static final String MIMETYPE = "mimetype";
    private static final String ID = "_ID";
    private static final String PHONE_TYPE = "data2";
    private static final String PHONE_NUMBER = "data1";

    // При помощи курсора двигаемся по контактам
    private Cursor cursorContacts;
    // Для получение данных по контакту через провайдер
    private ContentResolver contentResolver;
    // Отсюда получаем строки
    private Resources resources;

    public ContactRecyclerAdapter(Cursor cursorContacts, ContentResolver contentResolver, Resources resources){
        this.cursorContacts = cursorContacts;
        this.contentResolver = contentResolver;
        this.resources = resources;
    }

    @NonNull
    @Override
    public ContactRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecyclerAdapter.ViewHolder holder, int position) {
        String nameContact = "";
        String phoneContact = "";
        // Перейти на позицию в курсоре
        if (cursorContacts.moveToPosition(position)){
            // Взять из курсора столбец с именем
            nameContact = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            phoneContact = getFirstPhone();
        }
        holder.setTextName(nameContact);
        holder.setTextPhone(phoneContact);
    }

    // Получить из контактов первый телефон
    private String getFirstPhone() {
        String phoneContact = "";
        // получить идентификатор
        long contctId = cursorContacts.getLong(cursorContacts.getColumnIndex(ID));
        // Создаем курсор для получение дополнительных данных по контакту
        Cursor cursorData = contentResolver.query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.CONTACT_ID + " = " + contctId,
                null,
                null);
        if (cursorData == null){
            return phoneContact;
        }
        cursorData.moveToFirst();
        if (cursorData.getString(cursorData.getColumnIndex(MIMETYPE))
                .equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
            int phoneType = cursorData.getInt(cursorData.getColumnIndex(PHONE_TYPE));
            switch (phoneType) {
                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                    phoneContact = resources.getString(R.string.home);
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                    phoneContact = resources.getString(R.string.work);
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                    phoneContact = resources.getString(R.string.mobile);
                    break;
            }
            phoneContact += cursorData.getString(cursorData.getColumnIndex(PHONE_NUMBER));
        }
        return phoneContact;
    }

    @Override
    public int getItemCount() {
        return cursorContacts.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textNameContact);
            textPhone = itemView.findViewById(R.id.textPhoneContact);
        }

        public void setTextName(String name){
            textName.setText(name);
        }

        public void setTextPhone(String phone){
            textPhone.setText(phone);
        }
    }
}
