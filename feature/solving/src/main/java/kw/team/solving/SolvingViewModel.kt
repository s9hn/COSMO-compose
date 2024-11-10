package kw.team.solving

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kw.team.solving.model.AssessmentModel
import kw.team.solving.model.AssessmentTypeModel
import kw.team.solving.model.AssessmentsModel
import kw.team.solving.model.AssessmentsTypeModel
import kw.team.solving.model.QuestionModel
import kw.team.solving.model.SelectableOption
import kw.team.solving.model.SelectableOptions
import kw.team.solving.model.SubjectModel
import kw.team.subject.model.Subject
import javax.inject.Inject

@HiltViewModel
class SolvingViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(UiState.getIdleState())
    val state: StateFlow<UiState> = _state

    private val dummy = AssessmentsModel(
        index = 0,
        value = persistentListOf(
            AssessmentModel(
                questionModel = QuestionModel(
                    id = 1,
                    subject = SubjectModel.OperatingSystem,
                    assessmentsType = AssessmentsTypeModel.SUBJECT,
                    assessmentType = AssessmentTypeModel.MULTIPLE_CHOICE,
                    question = "권용민의 한자 뜻을 맞추어라",
                ),
                options = SelectableOptions(
                    value = persistentListOf(
                        SelectableOption(1, "권세 권, 쓸 용, 민첩할 민", false),
                        SelectableOption(2, "권세 권, 쓸 용, 백성 민", false),
                        SelectableOption(3, "권세 권, 녹 용, 천 민", false),
                        SelectableOption(4, "권세 권, 용 용, 옥돌 민", false),
                        SelectableOption(5, "권세 권, 얼굴 용, 옥돌 민", false),
                    ),
                    selectedIndex = null,
                ),
                isSaved = false,
            ),
            AssessmentModel(
                questionModel = QuestionModel(
                    id = 2,
                    subject = SubjectModel.OperatingSystem,
                    assessmentsType = AssessmentsTypeModel.SUBJECT,
                    assessmentType = AssessmentTypeModel.MULTIPLE_CHOICE,
                    question = "권용민의 가족 관계를 맞추어라",
                ),
                options = SelectableOptions(
                    value = persistentListOf(
                        SelectableOption(1, "누나 한 명", false),
                        SelectableOption(2, "누나 두 명", false),
                        SelectableOption(3, "여동생 한 명", false),
                        SelectableOption(4, "형 한 명", false),
                        SelectableOption(5, "남동생 한 명", false),
                    ),
                    selectedIndex = null,
                ),
                isSaved = false,
            )
        )
    )


    fun fetchAssessments(subject: Subject?) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            runCatching {
                // if(subject == null) 오늘의 학습 else 과목별 학습
                delay(1000L)
            }.onSuccess {
                _state.update { it.copy(assessments = dummy, isLoading = false) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun selectOption(index: Int) {
        _state.update {
            it.copy(assessments = it.assessments.selectOption(index))
        }
    }

    fun returnAnswer() {
        // 현재 답으로 제출
    }

    fun saveAssessment() {
        // 문제 북마크 로직 필요
    }

    data class UiState(
        val assessments: AssessmentsModel,
        val isLoading: Boolean,
        val isError: Boolean,
    ) {
        companion object {
            fun getIdleState(): UiState {
                return UiState(
                    assessments = AssessmentsModel(0, persistentListOf()),
                    isLoading = true,
                    isError = false
                )
            }
        }
    }
}