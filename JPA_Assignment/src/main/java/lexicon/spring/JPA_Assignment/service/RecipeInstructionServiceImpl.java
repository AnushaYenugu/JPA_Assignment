package lexicon.spring.JPA_Assignment.service;

import lexicon.spring.JPA_Assignment.data.RecipeInstructionRepository;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.RecipeInstructionForm;
import lexicon.spring.JPA_Assignment.model.entity.RecipeInstruction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeInstructionServiceImpl implements RecipeInstructionService{
    private final ModelMapper modelMapper;
    private final RecipeInstructionRepository recipeInstructionRepository;
@Autowired
    public RecipeInstructionServiceImpl(ModelMapper modelMapper, RecipeInstructionRepository recipeInstructionRepository) {
        this.modelMapper = modelMapper;
        this.recipeInstructionRepository = recipeInstructionRepository;
    }

    @Override
    public RecipeInstructionForm create(RecipeInstructionForm recipeInstructionForm) {
        RecipeInstruction recipeInstruction=modelMapper.map(recipeInstructionForm,RecipeInstruction.class);
        RecipeInstruction savedRecipeInstruction=recipeInstructionRepository.save(recipeInstruction);
        RecipeInstructionForm recipeInstructionFormMapped=modelMapper.map(savedRecipeInstruction,RecipeInstructionForm.class);
        return recipeInstructionFormMapped;
    }

    @Override
    public void update(RecipeInstructionForm recipeInstructionForm, Integer recipeInstructionId) {
        if(recipeInstructionId==null) throw new IllegalArgumentException("Recipe Instruction id is null");
        if(recipeInstructionRepository.existsById(recipeInstructionId)){
            RecipeInstruction recipeInstruction=modelMapper.map(recipeInstructionForm,RecipeInstruction.class);
            recipeInstructionRepository.save(recipeInstruction);
        }
}

    @Override
    public void deleteById(Integer recipeInstructionId) {
        if(recipeInstructionId==null) throw new IllegalArgumentException("Recipe Id is null");
        if(recipeInstructionRepository.existsById(recipeInstructionId)){
            recipeInstructionRepository.deleteById(recipeInstructionId);
        }
}

    @Override
    public List<RecipeInstructionForm> findAll() {
        Iterable<RecipeInstruction> recipeInstructionList=recipeInstructionRepository.findAll();
        List<RecipeInstructionForm> recipeInstructionFormList=modelMapper.map(recipeInstructionList
                ,new TypeToken<List<RecipeInstructionForm>>(){}.getType());

        return recipeInstructionFormList;
    }

    @Override
    public RecipeInstructionForm findById(Integer recipeInstructionId) {
        if(recipeInstructionId==null) throw new IllegalArgumentException("Recipe Id is null");
        RecipeInstruction foundByRecipeInstructionId=recipeInstructionRepository.findById(recipeInstructionId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Instruction Resource Not Found"));
        return modelMapper.map(foundByRecipeInstructionId,RecipeInstructionForm.class);
    }
}
