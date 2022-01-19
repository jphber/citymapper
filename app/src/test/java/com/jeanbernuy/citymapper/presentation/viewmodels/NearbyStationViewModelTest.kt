package com.jeanbernuy.citymapper.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeanbernuy.citymapper.core.AppConstants
import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.model.Arrivals
import com.jeanbernuy.citymapper.data.model.Routes
import com.jeanbernuy.citymapper.data.model.StopPoint
import com.jeanbernuy.citymapper.domain.StopPointRepository
import com.jeanbernuy.cookpad.utils.CoroutineTestRule
import com.jeanbernuy.cookpad.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

@ExperimentalCoroutinesApi
class NearbyStationViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: NearbyStationViewModel

    @RelaxedMockK
    private lateinit var repository: StopPointRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = NearbyStationViewModel(repository)
    }


    @Test
    fun should_ReturnResourceSuccess_When_IsCorrectStopPointsResult() = coroutineTestRule.runBlockingTest {
        //Given
        val stopPoints = StopPoint()
        coEvery {
            repository.fetchStopPoints(AppConstants.LATITUDE,
                AppConstants.LATITUDE,
                AppConstants.STOP_TYPES,
                AppConstants.RADIUS)
        } returns Resource.Success(stopPoints)
        //When
        val valueTwo = viewModel.fetchAllStopPoints(AppConstants.LATITUDE,
            AppConstants.LATITUDE,
            AppConstants.STOP_TYPES,
            AppConstants.RADIUS).getOrAwaitValue()
        //Then
        Assert.assertTrue(valueTwo is Resource.Success)
    }

    @Test
    fun should_ReturnResourceSuccess_When_IsCorrectArrivalPredictionsResult() = coroutineTestRule.runBlockingTest {
        //Given
        val arrivals = Arrivals()
        coEvery {
            repository.fetchListArrivalPredictions("RJ34342")
        } returns Resource.Success(arrivals)
        //When
        val valueTwo = viewModel.fetchArrivalPredictions("RJ34342").getOrAwaitValue()
        //Then
        Assert.assertTrue(valueTwo is Resource.Success)
    }

    @Test
    fun should_ReturnResourceSuccess_When_IsCorrectValidRoutesResult() = coroutineTestRule.runBlockingTest {
        //Given
        val routes = Routes()
        coEvery {
            repository.fetchAllValidRoutes("central","inbound")
        } returns Resource.Success(routes)
        //When
        val valueTwo = viewModel.fetchAllValidRoutes("central","inbound").getOrAwaitValue()
        //Then
        Assert.assertTrue(valueTwo is Resource.Success)
    }

    @Test
    fun should_ReturnResourceFailure_When_IsErrorStopPointsResult() = coroutineTestRule.runBlockingTest {
        //Given
        coEvery { repository.fetchStopPoints(AppConstants.LATITUDE,
            AppConstants.LATITUDE,
            AppConstants.STOP_TYPES,
            AppConstants.RADIUS) } returns Resource.Failure(Exception())
        //When
        val value = viewModel.fetchAllStopPoints(AppConstants.LATITUDE,
            AppConstants.LATITUDE,
            AppConstants.STOP_TYPES,
            AppConstants.RADIUS).getOrAwaitValue()
        //Then
        Assert.assertTrue(value is Resource.Failure)
    }

    @Test
    fun should_ReturnResourceFailure_When_IsErrorArrivalPredictionsResult() = coroutineTestRule.runBlockingTest {
        //Given
        coEvery { repository.fetchListArrivalPredictions("RJ34342") } returns Resource.Failure(Exception())
        //When
        val value = viewModel.fetchArrivalPredictions("RJ34342").getOrAwaitValue()
        //Then
        Assert.assertTrue(value is Resource.Failure)
    }

    @Test
    fun should_ReturnResourceFailure_When_IsErrorValidRoutesResult() = coroutineTestRule.runBlockingTest {
        //Given
        coEvery { repository.fetchAllValidRoutes("central","inbound") } returns Resource.Failure(Exception())
        //When
        val value = viewModel.fetchAllValidRoutes("central","inbound").getOrAwaitValue()
        //Then
        Assert.assertTrue(value is Resource.Failure)
    }

}