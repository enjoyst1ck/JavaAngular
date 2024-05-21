package EventHub.mappers;

import EventHub.dtos.IDto;
import EventHub.models.IModel;

public interface IMapper<M extends IModel, D extends IDto> {
    M toModel(D dto);
    D toDto(M model);
}