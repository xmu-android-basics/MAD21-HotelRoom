package cn.windwood.apps.roompicker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cn.windwood.apps.roompicker.R;
import cn.windwood.apps.roompicker.list.RoomListAdapter;

public class ListFragment extends Fragment {

    private RoomViewModel mViewModel;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            NavHostFragment.findNavController(ListFragment.this)
                           .navigate(R.id.action_ListFragment_to_AddFragment);
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),
                                                                mColumnCount));
        }
        final RoomListAdapter adapter = new RoomListAdapter();
        recyclerView.setAdapter(adapter);

        mViewModel = new ViewModelProvider(requireActivity()).get(RoomViewModel.class);

        mViewModel.getAllRooms().observe(getViewLifecycleOwner(), rooms -> {
            adapter.submitList(rooms);
        });

    }
}