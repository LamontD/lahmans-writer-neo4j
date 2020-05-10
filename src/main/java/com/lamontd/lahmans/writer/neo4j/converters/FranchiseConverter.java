/*
 * Copyright 2020 lamontdozierjr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
  */
package com.lamontd.lahmans.writer.neo4j.converters;

import com.lamontd.utils.transport.MappedTransportObjectConverter;
import com.lamontd.lahmans.writer.neo4j.model.Franchise;
import com.lamontd.lahmans.writer.neo4j.repositories.FranchiseRepository;
import com.lamontd.utils.transport.MappedTransportObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class FranchiseConverter extends MappedTransportObjectConverter<Franchise> {
    @Autowired
    private FranchiseRepository franchiseRepository;
    
    public FranchiseConverter() { super("com.lamontd.lahmans.reader.model.Franchise"); }

    @Override
    protected Franchise convert(MappedTransportObject transportObject) {
        Franchise franchise = new Franchise();
        Map<String, Object> attributes = transportObject.getAttributes();
        franchise.setId((String)attributes.get("id"));
        franchise.setName((String)attributes.get("name"));
        franchise.setActive(convertToBoolean(attributes.get("active")));
        return franchise;
        
    }

    @Override
    protected void store(Franchise object) {
        franchiseRepository.save(object);
    }
    
    
}
