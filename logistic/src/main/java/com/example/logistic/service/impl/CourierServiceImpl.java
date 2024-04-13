package com.example.logistic.service.impl;

import com.example.logistic.exception.NotFoundException;
import com.example.logistic.model.Courier;
import com.example.logistic.model.dto.RequestCourierDto;
import com.example.logistic.model.dto.ResponseCourierDto;
import com.example.logistic.model.mapper.CourierMapper;
import com.example.logistic.repository.CourierRepository;
import com.example.logistic.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;

    @Override
    public ResponseCourierDto postCourier(RequestCourierDto requestCourierDto) {
        Courier courier = CourierMapper.toEntity(requestCourierDto);

        return CourierMapper.toResponseDto(courierRepository.save(courier));
    }

    @Override
    public ResponseCourierDto getCourier(Integer courierId) {
        Optional<Courier> courierOptional = courierRepository.findById(courierId);
        if (courierOptional.isEmpty()) throw new NotFoundException("Курьер с запрашиваемым id не найден");

        return CourierMapper.toResponseDto(courierOptional.get());

    }

    @Override
    public ResponseCourierDto patchCourier(Integer courierId, RequestCourierDto requestCourierDto) {
        Optional<Courier> courierOptional = courierRepository.findById(courierId);
        if (courierOptional.isEmpty())
            throw new NotFoundException("Пользователь с запрашиваемым id не найден"); //проверку вынести в отдельный метод
        Courier courier = courierOptional.get();

        if (requestCourierDto.getName() != null && !requestCourierDto.getName().isBlank()) {
            courier.setName(requestCourierDto.getName());
        }
        if (requestCourierDto.getAge() != null && requestCourierDto.getAge() > 18) {
            courier.setAge(requestCourierDto.getAge());
        }

        return CourierMapper.toResponseDto(courierRepository.save(courier));
    }

    @Override
    public void deleteCourier(Integer courierId) {
        courierRepository.deleteById(courierId);
    }

    @Override
    public List<ResponseCourierDto> getCouriers() {
        return courierRepository.findAll().stream()
                .map(CourierMapper::toResponseDto)
                .toList();
    }
}
