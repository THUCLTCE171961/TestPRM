package com.example.myticaura.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myticaura.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    // --- BƯỚC 1: ĐỊNH NGHĨA TỌA ĐỘ CỦA CỬA HÀNG ---
    // Ví dụ: Tọa độ của một cửa hàng tại TP.HCM
    private static final double STORE_LATITUDE = 10.8411279;
    private static final double STORE_LONGITUDE = 106.8098831;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return view;
    }

    /**
     * Phương thức này được gọi khi bản đồ đã sẵn sàng để sử dụng.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // --- BƯỚC 2: CẬP NHẬT BẢN ĐỒ ĐỂ HIỂN THỊ VỊ TRÍ CỬA HÀNG ---

        // 1. Tạo một đối tượng LatLng cho vị trí cửa hàng
        LatLng storeLocation = new LatLng(STORE_LATITUDE, STORE_LONGITUDE);

        // 2. Thêm một Marker (dấu ghim) tại vị trí cửa hàng
        // .title() -> Tiêu đề chính khi nhấn vào marker
        // .snippet() -> Dòng mô tả phụ
        mMap.addMarker(new MarkerOptions()
                .position(storeLocation)
                .title("Cửa hàng của Member4")
                .snippet("Trường Đại học FPT TP.HCM, Quận 9"));

        // 3. Di chuyển camera của bản đồ đến vị trí cửa hàng và zoom lại gần
        // Số 17f là mức độ zoom (từ 2 đến 21), số càng lớn zoom càng gần.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLocation, 17f));

        // (Tùy chọn) Bật các nút điều khiển trên bản đồ
        mMap.getUiSettings().setZoomControlsEnabled(true); // Hiển thị nút +/- để zoom
        mMap.getUiSettings().setCompassEnabled(true); // Hiển thị la bàn
    }
}


