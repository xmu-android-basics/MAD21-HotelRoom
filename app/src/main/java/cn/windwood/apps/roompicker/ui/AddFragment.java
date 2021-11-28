package cn.windwood.apps.roompicker.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import cn.windwood.apps.roompicker.R;

public class AddFragment extends Fragment {

    private RoomViewModel mViewModel;

    public static final String RESULT_TAG = "room_result";

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_add, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText mEditRoomView = view.findViewById(R.id.edit_room);

        final Button button = view.findViewById(R.id.button_add);
        button.setOnClickListener(v -> {

            String roomNumber = mEditRoomView.getText().toString();

            if (TextUtils.isEmpty(roomNumber)) {
                Toast.makeText(requireContext(), "NO INPUT", Toast.LENGTH_LONG)
                     .show();

                return;
            }

            // TODO 增加房间号至 ViewModel 中
            mViewModel = new ViewModelProvider(requireActivity()).get(
                RoomViewModel.class);
            mViewModel.add(roomNumber);

            NavHostFragment.findNavController(AddFragment.this)
                           .navigate(R.id.action_AddFragment_to_ListFragment);
        });
    }
}