package com.safewalk.app;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter
        extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ContactViewHolder holder,
            int position
    ) {

        Contact contact = contacts.get(position);

        holder.contactName.setText(contact.getName());

        holder.contactPhone.setText(contact.getPhone());

        holder.alertButton.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(
                    Uri.parse("tel:" + contact.getPhone())
            );

            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ContactViewHolder
            extends RecyclerView.ViewHolder {

        TextView contactName;
        TextView contactPhone;

        Button alertButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName =
                    itemView.findViewById(R.id.contactName);

            contactPhone =
                    itemView.findViewById(R.id.contactPhone);

            alertButton =
                    itemView.findViewById(R.id.alertButton);
        }
    }
}