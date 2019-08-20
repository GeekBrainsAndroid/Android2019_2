package ru.geekbrains.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogBuilderFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // здесь то же самое, что и в предыдущем примере
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_dialog)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                        ((MainActivity) getActivity()).onDialogResult(getString(R.string.yes));
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                        ((MainActivity) getActivity()).onDialogResult(getString(R.string.no));
                    }
                })
                .setNeutralButton(R.string.dunno, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                        ((MainActivity) getActivity()).onDialogResult(getString(R.string.dunno));
                    }
                })
                .setMessage(R.string.message_dialog);
        return builder.create();
    }
}
