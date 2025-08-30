package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.status.Status;
import net.fmattaperdomo.model.status.gateways.StatusRepository;
import net.fmattaperdomo.r2dbc.entity.StatusEntity;
import net.fmattaperdomo.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StatusReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Status,
        StatusEntity,
        Integer,
        StatusReactiveRepository
> implements StatusRepository {
    public StatusReactiveRepositoryAdapter(StatusReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Status.class));
    }


}
